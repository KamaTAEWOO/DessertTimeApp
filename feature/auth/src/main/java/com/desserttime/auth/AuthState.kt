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
    val memberPickCategory1: Int = 0,
    val memberPickCategory2: Int = 0,
    val memberPickCategory3: Int = 0,
    val memberPickCategory4: Int = 0,
    val memberPickCategory5: Int = 0 // 0은 선택 안 함.
) : BaseState
