package com.desserttime.domain.model

data class RequestMemberSignUpData(
    val memberName: String,
    val memberEmail: String,
    val snsId: String,
    val signInSns: String,
    val birthYear: Int,
    val memberGender: String,
    val firstCity: String,
    val secondaryCity: String,
    val thirdCity: String,
    val isAgreeAD: String,
    val memberPickCategory1: Int,
    val memberPickCategory2: Int,
    val memberPickCategory3: Int,
    val memberPickCategory4: Int,
    val memberPickCategory5: Int
)
