package com.desserttime.auth

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.desserttime.auth.login.LoginResult
import com.desserttime.auth.login.google.googleLoginStart
import com.desserttime.auth.login.naver.naverWithLogin
import com.desserttime.auth.model.LoginMethod
import com.desserttime.core.base.BaseViewModel
import com.desserttime.domain.model.RequestInquiryData
import com.desserttime.domain.model.RequestMemberSignUpData
import com.desserttime.domain.repository.MemberInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import loginWithKakaoAccount
import timber.log.Timber
import javax.inject.Inject

/*
* 로그인 및 회원가입 시 사용되는 viewModel
* */

private const val TAG = "AuthViewModel::"

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val memberInfoRepository: MemberInfoRepository
) : BaseViewModel<AuthState, AuthEvent>(
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

    fun loginWithLogic(
        method: LoginMethod,
        context: Context,
        onNavigateToSignUpAgree: () -> Unit,
        onNavigateToHome: () -> Unit
    ) {
        viewModelScope.launch {
            val result = when (method) {
                LoginMethod.KAKAO -> loginWithKakaoAccount(context)
                LoginMethod.NAVER -> naverWithLogin(context)
                LoginMethod.GOOGLE -> googleLoginStart()
            }

            // 로그인 성공 시 회원가입 동의 화면으로 이동
            when (result) {
                is LoginResult.Success -> {
                    // Member 정보를 저장
                    saveMemberNameData(result.member.name)
                    saveMemberEmailData(result.member.email)
                    saveSnsIdData(result.member.token)
                    saveSignInSnsData(result.member.id)
                    delay(500)

                    val checkValidation = checkValidation(result.member.token)
                    printAllData()
                    // onNavigateToSignUpAgree()
                    if (checkValidation) {
                        onNavigateToHome()
                    } else {
                        onNavigateToSignUpAgree()
                    }
                }

                is LoginResult.Error -> {
                    Timber.e(result.message)
                }

                else -> {
                    Timber.e("Unknown error occurred during Kakao login")
                }
            }
        }
    }

    // 회원가입 데이터 저장 후 멤버 번호 정보 받기
    fun requestUserSignUp() {
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

        // return 값 받아오기
        memberInfoRepository.requestMemberSignUp(requestMemberSignUpData)
            .onEach {
                Timber.i("$TAG requestUserSignUp: $it")
            }
            .launchIn(viewModelScope)
    }

    // Validation Check
    private fun checkValidation(snsId: String): Boolean {
        Timber.i("$TAG checkValidation: $snsId")
        memberInfoRepository.requestMemberValidation(snsId)
            .onEach {
                Timber.i("$TAG checkValidation: $it")
            }
            .launchIn(viewModelScope)
        return true
    }

    // 문의하기
    fun requestSendInquiryData(email: String, content: String, onNavigateToInquiryComplete: () -> Unit) {
        memberInfoRepository.requestInquiry(RequestInquiryData(email, content))
            .onEach {
                Timber.i("$TAG requestInquiry: $it")
                onNavigateToInquiryComplete()
            }
            .launchIn(viewModelScope)
    }
}
