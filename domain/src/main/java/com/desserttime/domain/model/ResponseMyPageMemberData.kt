package com.desserttime.domain.model

data class ResponseMyPageMemberData(
    val success: Boolean,
    val timestamp: String, // ISO-8601 문자열 형태로 받아서 처리
    val statusCode: Int,
    val message: String,
    val data: MyPageMemberData
)

data class MyPageMemberData(
    val nickName: String,
    val usersReviewCount: Int,
    val usersTotalPoint: Int
)
