package com.desserttime.auth.login

import com.desserttime.domain.model.MemberProfileData

sealed class LoginResult {
    data class None(val message: String) : LoginResult()
    data class Success(val member: MemberProfileData) : LoginResult()
    data class Error(val message: String) : LoginResult()
}
