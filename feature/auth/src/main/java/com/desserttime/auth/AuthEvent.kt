package com.desserttime.auth

import com.desserttime.core.base.BaseEvent

sealed class AuthEvent : BaseEvent {

    data class RequestMemberNameData(
        val memberName: String
    ) : AuthEvent()

    data class RequestMemberEmailData(
        val memberEmail: String
    ) : AuthEvent()

    data class RequestSnsIdData(
        val snsId: String
    ) : AuthEvent()

    data class RequestSignInSnsData(
        val signInSns: String
    ) : AuthEvent()

    data class RequestBirthYearData(
        val birthYear: Int
    ) : AuthEvent()

    data class RequestMemberGenderData(
        val memberGender: String
    ) : AuthEvent()

    data class RequestFirstCityData(
        val firstCity: String
    ) : AuthEvent()

    data class RequestSecondaryCityData(
        val secondaryCity: String
    ) : AuthEvent()

    data class RequestThirdCityData(
        val thirdCity: String
    ) : AuthEvent()

    data class RequestIsAgreeADData(
        val isAgreeAD: String
    ) : AuthEvent()

    data class RequestMemberPickCategory1Data(
        val memberPickCategory1: String
    ) : AuthEvent()

    data class RequestMemberPickCategory2Data(
        val memberPickCategory2: String
    ) : AuthEvent()

    data class RequestMemberPickCategory3Data(
        val memberPickCategory3: String
    ) : AuthEvent()

    data class RequestMemberPickCategory4Data(
        val memberPickCategory4: String
    ) : AuthEvent()

    data class RequestMemberPickCategory5Data(
        val memberPickCategory5: String
    ) : AuthEvent()
}
