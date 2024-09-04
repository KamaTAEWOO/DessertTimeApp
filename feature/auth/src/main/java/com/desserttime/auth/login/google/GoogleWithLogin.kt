package com.desserttime.auth.login.google

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.desserttime.auth.login.LoginResult
import com.desserttime.core.BuildConfig
import com.desserttime.domain.model.MemberProfile
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

private const val TAG = "GoogleWithLogin"
private const val GOOGLE_LOGIN_PROVIDER = "google"

private lateinit var googleSignInClient: GoogleSignInClient
private lateinit var launcher: ActivityResultLauncher<Intent>
private var memberProfile: MemberProfile? = null
private var googleSignInAccount: GoogleSignInAccount? = null

suspend fun googleLoginStart(): LoginResult = suspendCancellableCoroutine { continuation ->
    launcher.launch(googleSignInClient.signInIntent)

    CoroutineScope(Dispatchers.Main).launch {
        while (googleSignInAccount == null) {
            delay(100)
        }

        try {
            val loginResult = googleWithLogin()
            continuation.resume(loginResult)
        } catch (e: Exception) {
            continuation.resumeWithException(Exception("$TAG GoogleSignIn Failed"))
        }
    }

    continuation.invokeOnCancellation {
        Timber.e("$TAG googleLoginStart: Coroutine canceled")
    }
}

@Composable
fun GoogleLoginInit(
    context: Context
) {
    // GoogleSignInOptions 설정
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
        .requestEmail()
        .build()

    googleSignInClient = GoogleSignIn.getClient(context, gso)

    // ActivityResultLauncher 선언
    launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                googleSignInAccount = task.getResult(Exception::class.java)
                CoroutineScope(Dispatchers.Main).launch {
                    when (val loginResult = googleWithLogin()) {
                        is LoginResult.Success -> {
                            Timber.i("$TAG GoogleSignIn Success: ${loginResult.member}")
                        }
                        is LoginResult.Error -> {
                            Timber.e("$TAG GoogleSignIn Failed: ${loginResult.message}")
                            Toast.makeText(context, "$TAG GoogleSign-In Failed", Toast.LENGTH_SHORT).show()
                        }

                        is LoginResult.None -> TODO()
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
                Toast.makeText(context, "GoogleSign-In Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

suspend fun googleWithLogin(): LoginResult = suspendCancellableCoroutine { continuation ->
    val account = googleSignInAccount
    if (account == null) {
        continuation.resumeWithException(Exception("$TAG GoogleSignInAccount is null"))
        return@suspendCancellableCoroutine
    }

    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
    Timber.i("$TAG firebaseAuthWithGoogle: ${account.idToken}")

    // Firebase Auth로 로그인
    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            Timber.i("$TAG signInWithCredential:success")
            val user = FirebaseAuth.getInstance().currentUser

            Timber.i("$TAG user: ${user?.uid ?: ""}") // 로그인 시 토큰 대신 사용 RbHSlTdF8vYKjUYonN9HG6ntrr02

            memberProfile = MemberProfile(
                id = GOOGLE_LOGIN_PROVIDER,
                name = user?.displayName ?: "",
                email = user?.email ?: "",
                token = user?.uid ?: "" // 매일 같은 지 확인하기
            )

            continuation.resume(LoginResult.Success(memberProfile ?: MemberProfile("", "", "", "")))
        } else {
            Timber.e("$TAG signInWithCredential:failure: ${task.exception}")
            continuation.resumeWithException(Exception("$TAG signInWithCredential:failure: ${task.exception?.message}"))
        }
    }.addOnCanceledListener {
        continuation.resumeWithException(Exception("$TAG signInWithCredential: canceled"))
    }
}
