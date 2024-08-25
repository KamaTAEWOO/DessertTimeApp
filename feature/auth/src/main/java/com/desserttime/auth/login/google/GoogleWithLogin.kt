package com.desserttime.auth.login.google

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.desserttime.auth.AuthViewModel
import com.desserttime.core.BuildConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import timber.log.Timber

private const val TAG = "GoogleWithLogin"
private const val GOOGLE_LOGIN_PROVIDER = "google"

private lateinit var googleSignInClient: GoogleSignInClient
private lateinit var launcher: ActivityResultLauncher<Intent>

fun googleWithLogin() {
    val signInIntent = googleSignInClient.signInIntent
    launcher.launch(signInIntent)
}

@Composable
fun GoogleLoginInit(
    context: Context,
    onNavigateToSignUpAgree: () -> Unit,
    authViewModel: AuthViewModel
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
                val account = task.getResult(Exception::class.java)
                googleWithLoginSuccess(account, onNavigateToSignUpAgree, authViewModel)
            } catch (e: Exception) {
                Timber.e(e)
                Toast.makeText(context, "Google Sign-In Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

fun googleWithLoginSuccess(
    account: GoogleSignInAccount?,
    onNavigateToSignUpAgree: () -> Unit,
    authViewModel: AuthViewModel
) {
    val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
    Timber.i("firebaseAuthWithGoogle: ${account?.idToken}")

    // Firebase Auth로 로그인
     FirebaseAuth.getInstance().signInWithCredential(credential)
         .addOnCompleteListener { task ->
             if (task.isSuccessful) {
                 Timber.i("signInWithCredential:success")
                 val user = FirebaseAuth.getInstance().currentUser
                 Timber.i("signInWithCredential:success: ${user?.displayName}")
                 Timber.i("signInWithCredential:success: ${user?.email}")
                 Timber.i("signInWithCredential:success: ${user?.photoUrl}")
                 Timber.i("signInWithCredential:success: ${user?.uid}")
                 Timber.i("signInWithCredential:success: ${user?.providerData}")

                 // 데이터 저장
                 authViewModel.saveMemberNameData(user?.displayName ?: "")
                 authViewModel.saveMemberEmailData(user?.email ?: "")
                 authViewModel.saveSnsIdData(account?.idToken ?: "")
                 authViewModel.saveSignInSnsData(GOOGLE_LOGIN_PROVIDER)

                 // 화면 전환
                 onNavigateToSignUpAgree()
             } else {
                 Timber.i("signInWithCredential:failure: ${task.exception}")
             }
         }
}
