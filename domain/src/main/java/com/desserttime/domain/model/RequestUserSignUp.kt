package com.desserttime.domain.model

data class RequestUserSignUp(
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
    val memberPickCategory1: String,
    val memberPickCategory2: String,
    val memberPickCategory3: String,
    val memberPickCategory4: String,
    val memberPickCategory5: String
)
