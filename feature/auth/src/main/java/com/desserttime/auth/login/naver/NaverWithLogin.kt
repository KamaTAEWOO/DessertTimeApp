package com.desserttime.auth.login.naver

import android.content.Context
import com.desserttime.auth.AuthViewModel
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

private const val TAG = "NaverWithLogin"

fun naverWithLogin(
    context: Context,
    onNavigateToSignUpAgree: () -> Unit,
    authViewModel: AuthViewModel
) {
    val oauthLoginCallback = object : OAuthLoginCallback {
        override fun onSuccess() {
            // 네이버 로그인 인증이 성공했을 때 수행할 코드 추가
            Timber.i("$TAG 로그인 성공")
            Timber.i("$TAG AccessToken -> ${NaverIdLoginSDK.getAccessToken()}")
            Timber.i("$TAG RefreshToken -> ${NaverIdLoginSDK.getRefreshToken()}")
            Timber.i("$TAG Expires -> ${NaverIdLoginSDK.getExpiresAt()}")
            Timber.i("$TAG Type -> ${NaverIdLoginSDK.getTokenType()}")
            Timber.i("$TAG State -> ${NaverIdLoginSDK.getState()}")

            CoroutineScope(Dispatchers.Main).launch {
                val result = authViewModel.fetchNaverUserInfo()

                result.onSuccess {
                    Timber.i("$TAG Naver login success")
                    onNavigateToSignUpAgree()
                }
                result.onFailure {
                    Timber.e(it, "$TAG Error occurred during Naver login")
                }
            }
        }
        override fun onFailure(httpStatus: Int, message: String) {
            val errorCode = NaverIdLoginSDK.getLastErrorCode().code
            val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
            Timber.i("$TAG errorCode:$errorCode, errorDesc:$errorDescription")
        }
        override fun onError(errorCode: Int, message: String) {
            onFailure(errorCode, message)
        }
    }
    NaverIdLoginSDK.authenticate(context, oauthLoginCallback)
}
