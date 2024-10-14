package com.desserttime.domain.model

data class RequestMyPageMemberSaveData(
    val memberId: String,
    val birth: String,
    val gender: String,
    val firstCity: String,
    val secondCity: String,
    val thirdCity: String,
    val nickName: String
)
