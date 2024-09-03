package com.desserttime.auth.login.naver

import android.content.Context
import com.desserttime.auth.login.LoginResult
import com.desserttime.auth.model.MemberProfile
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.OAuthLoginCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import timber.log.Timber
import kotlin.coroutines.resume

private const val TAG = "NaverWithLogin"
const val NAVER_LOGIN_PROVIDER = "naver"

suspend fun naverWithLogin(context: Context): LoginResult = withContext(Dispatchers.IO) {
    suspendCancellableCoroutine { continuation ->
        val oauthLoginCallback = object : OAuthLoginCallback {
            override fun onSuccess() {
                Timber.i("$TAG 로그인 성공")
                // Proceed to fetch user info
                launch {
                    continuation.resume(fetchNaverUserInfo())
                }
            }

            override fun onFailure(httpStatus: Int, message: String) {
                val errorCode = NaverIdLoginSDK.getLastErrorCode().code
                val errorDescription = NaverIdLoginSDK.getLastErrorDescription()
                Timber.i("$TAG errorCode:$errorCode, errorDesc:$errorDescription")
                continuation.resume(LoginResult.Error("Login failed: $message"))
            }

            override fun onError(errorCode: Int, message: String) {
                onFailure(errorCode, message)
            }
        }
        NaverIdLoginSDK.authenticate(context, oauthLoginCallback)
    }
}

// 네이버 로그인 사용자 정보 가져오기
suspend fun fetchNaverUserInfo(): LoginResult {
    val accessToken = NaverIdLoginSDK.getAccessToken()

    return if (accessToken != null) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://openapi.naver.com/v1/nid/me")
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        withContext(Dispatchers.IO) {
            try {
                val response: Response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                if (response.isSuccessful && responseBody != null) {
                    val jsonObject = JSONObject(responseBody)
                    val responseObj = jsonObject.getJSONObject("response")
                    val name = responseObj.getString("name")
                    val email = responseObj.getString("email")
                    val id = responseObj.getString("id")

                    Timber.i("$TAG id: $id") // 로그인 시 토큰 대신 사용 7ImgfubjaYVScQZf-N-gES7YwmZeQhPP8E2wMrHxINU

                    LoginResult.Success(
                        MemberProfile(
                            id = NAVER_LOGIN_PROVIDER,
                            name = name,
                            email = email,
                            token = id
                        )
                    )
                } else {
                    // 실패 시 에러 반환
                    LoginResult.Error("Failed to fetch user info: ${response.message}")
                }
            } catch (e: Exception) {
                // 예외 발생 시 실패 반환
                LoginResult.Error("Failed to fetch user info: ${e.message}")
            }
        }
    } else {
        // 액세스 토큰이 null인 경우 실패 처리
        LoginResult.Error("Failed to fetch user info: Access token is null")
    }
}
