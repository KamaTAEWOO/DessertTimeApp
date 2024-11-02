package com.desserttime.auth.login

import com.desserttime.domain.model.MemberProfileData

sealed class LoginResult {
    data class LOADING(val message: String) : LoginResult()
    data class SUCCESS(val member: MemberProfileData) : LoginResult()
    data class ERROR(val message: String) : LoginResult()
}
