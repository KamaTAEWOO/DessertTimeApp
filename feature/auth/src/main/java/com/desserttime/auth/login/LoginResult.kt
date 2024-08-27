package com.desserttime.auth.login

import com.desserttime.auth.model.UserProfile

sealed class LoginResult {
    data class None(val message: String) : LoginResult()
    data class Success(val user: UserProfile) : LoginResult()
    data class Error(val message: String) : LoginResult()
}