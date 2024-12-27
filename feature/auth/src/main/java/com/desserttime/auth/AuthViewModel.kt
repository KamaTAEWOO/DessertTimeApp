package com.desserttime.auth

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.desserttime.auth.login.LoginResult
import com.desserttime.auth.login.google.googleLoginStart
import com.desserttime.auth.login.naver.naverWithLogin
import com.desserttime.auth.model.LoginMethodData
import com.desserttime.core.base.BaseViewModel
import com.desserttime.core.utility.MemberDataManager
import com.desserttime.core.utility.SharedPreferencesManager
import com.desserttime.domain.model.RequestInquiryData
import com.desserttime.domain.model.RequestMemberSignUpData
import com.desserttime.domain.model.TokenData
import com.desserttime.domain.repository.MemberInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import loginWithKakaoAccount
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val memberInfoRepository: MemberInfoRepository,
    private val sharedPreferencesManager: SharedPreferencesManager
) : BaseViewModel<AuthState, AuthEvent>(
    initialState = AuthState()
) {
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _snsId = mutableStateOf("")
    val snsId: State<String> get() = _snsId

    private fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

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

    private fun saveMemberNameData(memberName: String) {
        sendAction(AuthEvent.RequestMemberNameData(memberName))
    }

    private fun saveMemberEmailData(memberEmail: String) {
        sendAction(AuthEvent.RequestMemberEmailData(memberEmail))
    }

    private fun saveSnsIdData(snsId: String) {
        sendAction(AuthEvent.RequestSnsIdData(snsId))
    }

    private fun saveSignInSnsData(signInSns: String) {
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

    fun saveMemberPickCategory1Data(memberPickCategory1: Int) {
        sendAction(AuthEvent.RequestMemberPickCategory1Data(memberPickCategory1))
    }

    fun saveMemberPickCategory2Data(memberPickCategory2: Int) {
        sendAction(AuthEvent.RequestMemberPickCategory2Data(memberPickCategory2))
    }

    fun saveMemberPickCategory3Data(memberPickCategory3: Int) {
        sendAction(AuthEvent.RequestMemberPickCategory3Data(memberPickCategory3))
    }

    fun saveMemberPickCategory4Data(memberPickCategory4: Int) {
        sendAction(AuthEvent.RequestMemberPickCategory4Data(memberPickCategory4))
    }

    fun saveMemberPickCategory5Data(memberPickCategory5: Int) {
        sendAction(AuthEvent.RequestMemberPickCategory5Data(memberPickCategory5))
    }

    fun loginWithLogic(
        method: LoginMethodData,
        context: Context,
        onNavigateToSignUpAgree: () -> Unit,
        onNavigateToHome: () -> Unit
    ) {
        viewModelScope.launch {
            val result = when (method) {
                LoginMethodData.KAKAO -> loginWithKakaoAccount(context)
                LoginMethodData.NAVER -> naverWithLogin(context)
                LoginMethodData.GOOGLE -> googleLoginStart()
            }
            when (result) {
                is LoginResult.SUCCESS -> {
                    // Member 정보를 저장
                    saveMemberNameData(result.member.name)
                    saveMemberEmailData(result.member.email)
                    saveSnsIdData(result.member.token)
                    saveSignInSnsData(result.member.id)
                    delay(500)

                    checkValidation(
                        result.member.token,
                        onNavigateToSignUpAgree,
                        onNavigateToHome
                    )
                }

                is LoginResult.ERROR -> {
                }

                is LoginResult.LOADING -> {
                }
            }
        }
    }

    private fun saveMemberData(memberData: TokenData) {
        sharedPreferencesManager.saveMemberData(memberData)
    }

    // 회원가입 데이터 저장 후 멤버 번호 정보 받기
    fun requestUserSignUp(onNavigateToSignUpComplete: () -> Unit) {
        val currentState = uiState.value
        val requestMemberSignUpData = RequestMemberSignUpData(
            memberName = currentState.memberName,
            memberEmail = currentState.memberEmail,
            snsId = currentState.snsId,
            signInSns = currentState.signInSns,
            birthYear = currentState.birthYear,
            memberGender = currentState.memberGender,
            firstCity = currentState.firstCity,
            secondaryCity = currentState.secondaryCity,
            thirdCity = currentState.thirdCity,
            isAgreeAD = currentState.isAgreeAD,
            memberPickCategory1 = currentState.memberPickCategory1,
            memberPickCategory2 = currentState.memberPickCategory2,
            memberPickCategory3 = currentState.memberPickCategory3,
            memberPickCategory4 = currentState.memberPickCategory4,
            memberPickCategory5 = currentState.memberPickCategory5
        )

        setLoading(true)

        memberInfoRepository.requestMemberSignUp(requestMemberSignUpData)
            .onEach {
                onNavigateToSignUpComplete()
            }
            .catch { e ->
                Timber.e(e)
            }
            .onCompletion {
                setLoading(false)
            }
            .launchIn(viewModelScope)
    }

    fun checkValidation(
        snsId: String,
        onNavigateToSignUpAgree: () -> Unit = {},
        onNavigateToHome: () -> Unit = {}
    ): Boolean {
        if (snsId.isEmpty()) {
            onNavigateToSignUpAgree()
            return false
        }

        setLoading(true)

        memberInfoRepository.requestMemberValidation(snsId)
            .onEach { response ->
                Timber.d("response.token: ${response.data.token}")
                Timber.d("response.success: ${response.data.memberId}, ${response.data.nickName}")
                if (response.success) {
                    sharedPreferencesManager.saveMemberData(response.data)

                    MemberDataManager.saveData(response.data)
                    onNavigateToHome()
                } else {
                    onNavigateToSignUpAgree()
                }
            }
            .catch { e ->
                Timber.e(e)
                if (e.message == "HTTP 400 Bad Request") {
                    onNavigateToSignUpAgree()
                }
            }
            .onCompletion {
                setLoading(false) // Set loading false here, in completion
            }
            .launchIn(viewModelScope)

        return true
    }

    fun requestSendInquiryData(email: String, content: String, onNavigateToInquiryComplete: () -> Unit) {
        setLoading(true)

        memberInfoRepository.requestInquiry(RequestInquiryData(email, content))
            .onEach {
                onNavigateToInquiryComplete()
            }
            .onCompletion {
                setLoading(false)
            }
            .launchIn(viewModelScope)
    }
}
