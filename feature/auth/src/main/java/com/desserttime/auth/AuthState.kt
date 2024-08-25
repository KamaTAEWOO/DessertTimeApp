package com.desserttime.auth

import com.desserttime.core.base.BaseState

data class AuthState(
    val memberName: String = "",
    val memberEmail: String = "",
    val snsId: String = "",
    val signInSns: String = "",
    val birthYear: Int = 0,
    val memberGender: String = "",
    val firstCity: String = "",
    val secondaryCity: String = "",
    val thirdCity: String = "",
    val isAgreeAD: String = "",
    val memberPickCategory1: String = "",
    val memberPickCategory2: String = "",
    val memberPickCategory3: String = "",
    val memberPickCategory4: String = "",
    val memberPickCategory5: String = ""
) : BaseState
