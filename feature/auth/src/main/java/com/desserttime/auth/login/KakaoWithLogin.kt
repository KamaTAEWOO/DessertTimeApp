package com.desserttime.auth.login

import android.content.Context
import com.kakao.sdk.user.UserApiClient
import timber.log.Timber

private val TAG = "KakaoWithLogin"

fun loginWithKakao(context: Context) {
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        // Try KakaoTalk login first
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                // Handle error, fallback to Kakao account login if necessary
                Timber.i("$TAG Login failed: ${error.message}")

                // Optional: Fallback to web login
                loginWithKakaoAccount(context)
            } else if (token != null) {
                // Handle successful login
                Timber.i("$TAG Login successful. Token: ${token.accessToken}")
                fetchKakaoUserInfo()
            }
        }
    } else {
        // KakaoTalk is not installed, fallback to Kakao Account login
        loginWithKakaoAccount(context)
    }
}

fun loginWithKakaoAccount(context: Context) {
    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
        if (error != null) {
            Timber.i("$TAG Login failed: ${error.message}")
        } else if (token != null) {
            Timber.i("$TAG Login successful. Token: ${token.accessToken}")
            fetchKakaoUserInfo()
        }
    }
}

fun fetchKakaoUserInfo() {
    UserApiClient.instance.me { user, error ->
        if (error != null) {
            Timber.i("$TAG Failed to get user info: ${error.message}")
        } else if (user != null) {
            val userId = user.id
            val userEmail = user.kakaoAccount?.email
            val userConnect = user.connectedAt // Login time
            Timber.i("$TAG User ID: $userId")
            Timber.i("$TAG User Email: $userEmail")
            Timber.i("$TAG User Connected At: $userConnect")
        }
    }
}
