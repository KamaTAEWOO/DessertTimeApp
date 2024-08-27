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
import com.desserttime.auth.model.UserProfile
import com.desserttime.core.BuildConfig
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
private var userProfile: UserProfile? = null
private var googleSignInAccount: GoogleSignInAccount? = null

suspend fun googleLoginStart(): LoginResult {
    val signInIntent = googleSignInClient.signInIntent
    launcher.launch(signInIntent)

    // 로그인 성공 시 userProfile 반환
    delay(2000)
    return if (userProfile != null) {
        userProfile?.let { LoginResult.Success(it) } ?: LoginResult.None("GoogleSignIn Failed")
    } else {
        // 로그인 실패 시
        LoginResult.Error("GoogleSignIn Failed")
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
                            Timber.i("GoogleSignIn Success: ${loginResult.user}")
                        }
                        is LoginResult.Error -> {
                            Timber.i("GoogleSignIn Failed: ${loginResult.message}")
                            Toast.makeText(context, "GoogleSign-In Failed", Toast.LENGTH_SHORT).show()
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
        continuation.resumeWithException(Exception("GoogleSignInAccount is null"))
        return@suspendCancellableCoroutine
    }

    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
    Timber.i("firebaseAuthWithGoogle: ${account.idToken}")

    // Firebase Auth로 로그인
    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            Timber.i("signInWithCredential:success")
            val user = FirebaseAuth.getInstance().currentUser

            userProfile = UserProfile(
                id = GOOGLE_LOGIN_PROVIDER,
                name = user?.displayName ?: "",
                email = user?.email ?: "",
                token = account.idToken ?: ""
            )

            continuation.resume(LoginResult.Success(userProfile ?: UserProfile("", "", "", "")))
        } else {
            Timber.i("signInWithCredential:failure: ${task.exception}")
            continuation.resumeWithException(Exception("signInWithCredential:failure: ${task.exception?.message}"))
        }
    }.addOnCanceledListener {
        continuation.resumeWithException(Exception("signInWithCredential: canceled"))
    }
}
