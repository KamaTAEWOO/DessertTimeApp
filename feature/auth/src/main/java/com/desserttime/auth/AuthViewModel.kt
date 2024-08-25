package com.desserttime.auth

import android.content.Context
import com.desserttime.core.base.BaseViewModel
import com.navercorp.nid.NaverIdLoginSDK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import timber.log.Timber
import javax.inject.Inject

/*
* 로그인 및 회원가입 시 사용되는 viewModel
* */

private const val TAG = "AuthViewModel::"
private const val NAVER_LOGIN_PROVIDER = "naver"

@HiltViewModel
class AuthViewModel @Inject constructor() : BaseViewModel<AuthState, AuthEvent>(
    initialState = AuthState()
) {
    override fun reduceState(currentState: AuthState, event: AuthEvent): AuthState {
        return when (event) {
            is AuthEvent.RequestMemberNameData -> {
                currentState.copy(memberName = event.memberName)
            }
            is AuthEvent.RequestMemberEmailData -> {
                currentState.copy(memberEmail = event.memberEmail)
            }
            is AuthEvent.RequestSnsIdData -> {
                currentState.copy(snsId = event.snsId)
            }
            is AuthEvent.RequestSignInSnsData -> {
                currentState.copy(signInSns = event.signInSns)
            }
            is AuthEvent.RequestBirthYearData -> {
                currentState.copy(birthYear = event.birthYear)
            }
            is AuthEvent.RequestMemberGenderData -> {
                currentState.copy(memberGender = event.memberGender)
            }
            is AuthEvent.RequestFirstCityData -> {
                currentState.copy(firstCity = event.firstCity)
            }
            is AuthEvent.RequestSecondaryCityData -> {
                currentState.copy(secondaryCity = event.secondaryCity)
            }
            is AuthEvent.RequestThirdCityData -> {
                currentState.copy(thirdCity = event.thirdCity)
            }
            is AuthEvent.RequestIsAgreeADData -> {
                currentState.copy(isAgreeAD = event.isAgreeAD)
            }
            is AuthEvent.RequestMemberPickCategory1Data -> {
                currentState.copy(memberPickCategory1 = event.memberPickCategory1)
            }
            is AuthEvent.RequestMemberPickCategory2Data -> {
                currentState.copy(memberPickCategory2 = event.memberPickCategory2)
            }
            is AuthEvent.RequestMemberPickCategory3Data -> {
                currentState.copy(memberPickCategory3 = event.memberPickCategory3)
            }
            is AuthEvent.RequestMemberPickCategory4Data -> {
                currentState.copy(memberPickCategory4 = event.memberPickCategory4)
            }
            is AuthEvent.RequestMemberPickCategory5Data -> {
                currentState.copy(memberPickCategory5 = event.memberPickCategory5)
            }
        }
    }

    fun saveMemberNameData(memberName: String) {
        sendAction(AuthEvent.RequestMemberNameData(memberName))
    }

    fun saveMemberEmailData(memberEmail: String) {
        sendAction(AuthEvent.RequestMemberEmailData(memberEmail))
    }

    fun saveSnsIdData(snsId: String) {
        sendAction(AuthEvent.RequestSnsIdData(snsId))
    }

    fun saveSignInSnsData(signInSns: String) {
        sendAction(AuthEvent.RequestSignInSnsData(signInSns))
    }

    fun saveBirthYearData(birthYear: Int) {
        sendAction(AuthEvent.RequestBirthYearData(birthYear))
    }

    fun saveMemberGenderData(memberGender: String) {
        sendAction(AuthEvent.RequestMemberGenderData(memberGender))
    }

    fun saveFirstCityData(firstCity: String) {
        sendAction(AuthEvent.RequestFirstCityData(firstCity))
    }

    fun saveSecondaryCityData(secondaryCity: String) {
        sendAction(AuthEvent.RequestSecondaryCityData(secondaryCity))
    }

    fun saveThirdCityData(thirdCity: String) {
        sendAction(AuthEvent.RequestThirdCityData(thirdCity))
    }

    fun saveIsAgreeADData(isAgreeAD: String) {
        sendAction(AuthEvent.RequestIsAgreeADData(isAgreeAD))
    }

    fun saveMemberPickCategory1Data(memberPickCategory1: String) {
        sendAction(AuthEvent.RequestMemberPickCategory1Data(memberPickCategory1))
    }

    fun saveMemberPickCategory2Data(memberPickCategory2: String) {
        sendAction(AuthEvent.RequestMemberPickCategory2Data(memberPickCategory2))
    }

    fun saveMemberPickCategory3Data(memberPickCategory3: String) {
        sendAction(AuthEvent.RequestMemberPickCategory3Data(memberPickCategory3))
    }

    fun saveMemberPickCategory4Data(memberPickCategory4: String) {
        sendAction(AuthEvent.RequestMemberPickCategory4Data(memberPickCategory4))
    }

    fun saveMemberPickCategory5Data(memberPickCategory5: String) {
        sendAction(AuthEvent.RequestMemberPickCategory5Data(memberPickCategory5))
    }

    // 전체 변수 로그 찍기
    fun printAllData() {
        val currentState = uiState.value
        Timber.i("$TAG memberName: ${currentState.memberName}")
        Timber.i("$TAG memberEmail: ${currentState.memberEmail}")
        Timber.i("$TAG snsId: ${currentState.snsId}")
        Timber.i("$TAG signInSns: ${currentState.signInSns}")
        Timber.i("$TAG birthYear: ${currentState.birthYear}")
        Timber.i("$TAG memberGender: ${currentState.memberGender}")
        Timber.i("$TAG firstCity: ${currentState.firstCity}")
        Timber.i("$TAG secondaryCity: ${currentState.secondaryCity}")
        Timber.i("$TAG thirdCity: ${currentState.thirdCity}")
        Timber.i("$TAG isAgreeAD: ${currentState.isAgreeAD}")
        Timber.i("$TAG memberPickCategory1: ${currentState.memberPickCategory1}")
        Timber.i("$TAG memberPickCategory2: ${currentState.memberPickCategory2}")
        Timber.i("$TAG memberPickCategory3: ${currentState.memberPickCategory3}")
        Timber.i("$TAG memberPickCategory4: ${currentState.memberPickCategory4}")
        Timber.i("$TAG memberPickCategory5: ${currentState.memberPickCategory5}")
    }

    // 네이버 로그인 사용자 정보 가져오기
    suspend fun fetchNaverUserInfo(): Result<String> {
        val accessToken = NaverIdLoginSDK.getAccessToken()

        if (accessToken != null) {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://openapi.naver.com/v1/nid/me")
                .addHeader("Authorization", "Bearer $accessToken")
                .build()

            return withContext(Dispatchers.IO) {
                try {
                    val response: Response = client.newCall(request).execute()
                    val responseBody = response.body?.string()

                    if (response.isSuccessful && responseBody != null) {
                        val jsonObject = JSONObject(responseBody)
                        val responseObj = jsonObject.getJSONObject("response")
                        val name = responseObj.getString("name")
                        val email = responseObj.getString("email")
                        val nickname = responseObj.getString("nickname")

                        saveMemberNameData(name)
                        saveMemberEmailData(email)
                        saveSnsIdData(accessToken)
                        saveSignInSnsData(NAVER_LOGIN_PROVIDER)

                        // 성공한 결과로 데이터를 반환
                        Result.success("User Info: Name -> $name, Email -> $email, Nickname -> $nickname")
                    } else {
                        // 실패한 경우 에러 메시지 반환
                        Result.failure(Exception("Failed to fetch user info. Response: $responseBody"))
                    }
                } catch (e: Exception) {
                    // 예외 발생 시 실패 처리
                    Result.failure(e)
                }
            }
        } else {
            // 액세스 토큰이 null인 경우 실패 처리
            return Result.failure(Exception("AccessToken is null. Cannot fetch user info."))
        }
    }

}