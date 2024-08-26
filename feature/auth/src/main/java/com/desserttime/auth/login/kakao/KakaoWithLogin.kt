package com.desserttime.auth.login.kakao

import android.content.Context
import com.desserttime.auth.AuthViewModel
import com.desserttime.auth.model.UserProfile
import com.kakao.sdk.user.UserApiClient
import timber.log.Timber

private const val TAG = "KakaoWithLogin"
private const val KAKAO_LOGIN_PROVIDER = "kakao"

fun loginWithKakao(
    context: Context,
    onNavigateToSignUpAgree: () -> Unit,
    authViewModel: AuthViewModel
) {
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        // Try KakaoTalk login first
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                // Handle error, fallback to Kakao account login if necessary
                Timber.i("$TAG Login failed: ${error.message}")

                // Optional: Fallback to web login
                loginWithKakaoAccount(context, onNavigateToSignUpAgree, authViewModel)
            } else if (token != null) {
                // Handle successful login
                Timber.i("$TAG Login successful. Token: ${token.accessToken}")
                fetchKakaoUserInfo(onNavigateToSignUpAgree, authViewModel, token.accessToken)
            }
        }
    } else {
        // KakaoTalk is not installed, fallback to Kakao Account login
        loginWithKakaoAccount(context, onNavigateToSignUpAgree, authViewModel)
    }
}

fun loginWithKakaoAccount(
    context: Context,
    onNavigateToSignUpAgree: () -> Unit,
    authViewModel: AuthViewModel
) {
    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
        if (error != null) {
            Timber.i("$TAG Login failed: ${error.message}")
        } else if (token != null) {
            Timber.i("$TAG Login successful. Token: ${token.accessToken}")
            fetchKakaoUserInfo(onNavigateToSignUpAgree, authViewModel, token.accessToken)
        }
    }
}

fun fetchKakaoUserInfo(
    onNavigateToSignUpAgree: () -> Unit,
    authViewModel: AuthViewModel,
    accessToken: String
) {
    UserApiClient.instance.me { user, error ->
        if (error != null) {
            Timber.i("$TAG Failed to get user info: ${error.message}")
        } else if (user != null) {
            val userId = user.id
            val userEmail = user.kakaoAccount?.email
            Timber.i("$TAG User ID: $userId")
            Timber.i("$TAG User Email: $userEmail")

            val userProfile = UserProfile(
                id = userId.toString(),
                name = user.kakaoAccount?.profile?.nickname.toString(),
                email = userEmail.toString(),
                token = accessToken
            )

            // 화면 전환
            onNavigateToSignUpAgree()
        }
    }
}
