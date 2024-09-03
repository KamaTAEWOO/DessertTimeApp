package com.desserttime.auth.login

import com.desserttime.auth.model.MemberProfile

sealed class LoginResult {
    data class None(val message: String) : LoginResult()
    data class Success(val member: MemberProfile) : LoginResult()
    data class Error(val message: String) : LoginResult()
}
