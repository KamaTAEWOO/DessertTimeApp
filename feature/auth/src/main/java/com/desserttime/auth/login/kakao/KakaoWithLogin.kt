import android.content.Context
import com.desserttime.auth.login.LoginResult
import com.desserttime.auth.model.UserProfile
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

private const val TAG = "KakaoWithLogin"
private const val KAKAO_LOGIN_PROVIDER = "kakao"

suspend fun loginWithKakaoAccount(
    context: Context
): LoginResult {
    return try {
        // Kakao 계정으로 로그인하는 suspend 함수
        val token = loginWithKakaoAccountSuspend(context)

        // 로그인 성공 시 유저 정보를 가져옴
        val userInfoResult = fetchKakaoUserInfo(token.accessToken)

        // 성공적으로 로그인 및 사용자 정보 가져왔을 경우
        userInfoResult
    } catch (e: Exception) {
        // 로그인 실패 시
        Timber.i("$TAG Login failed: ${e.message}")
        LoginResult.Error("Kakao Account login failed: ${e.message}")
    }
}

private suspend fun loginWithKakaoAccountSuspend(context: Context): OAuthToken {
    return suspendCancellableCoroutine { continuation ->
        UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
            if (error != null) {
                // 에러 발생 시 코루틴을 종료하고 예외를 발생시킴
                continuation.resumeWithException(error)
            } else if (token != null) {
                // 토큰을 성공적으로 받아온 경우 코루틴을 재개시킴
                continuation.resume(token)
            }
        }
    }
}

suspend fun fetchKakaoUserInfo(accessToken: String): LoginResult {
    return suspendCancellableCoroutine { continuation ->
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Timber.i("$TAG Failed to get user info: ${error.message}")
                continuation.resume(LoginResult.Error("Failed to fetch user info: ${error.message}"))
            } else if (user != null) {
                val userProfile = UserProfile(
                    id = KAKAO_LOGIN_PROVIDER,
                    name = user.kakaoAccount?.profile?.nickname.orEmpty(),
                    email = user.kakaoAccount?.email.orEmpty(),
                    token = accessToken
                )
                continuation.resume(LoginResult.Success(userProfile))
            }
        }
    }
}