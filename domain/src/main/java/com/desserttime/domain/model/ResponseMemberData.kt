package com.desserttime.domain.model

data class ResponseMemberData(
    val success: Boolean,
    val timestamp: String, // ISO-8601 문자열 형태로 받아서 처리
    val statusCode: Int,
    val message: String,
    val data: MemberData
)

data class MemberData(
    val memberId: Int,
    val gender: String,
    val nickName: String,
    val birthYear: Int,
    val firstCity: String,
    val secondaryCity: String,
    val thirdCity: String,
    val profileImgMiddlePath: String?,
    val profileImgId: String?,
    val profileImgPath: String?,
    val profileImgExtension: String?,
    val desserts: List<Dessert>
)

data class Dessert(
    val dessertCategoryId: Int,
    val dessertName: String
)
