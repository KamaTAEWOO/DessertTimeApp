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
    val snsId: String,
    val signInSns: String,
    val memberEmail: String,
    val memberName: String,
    val nickName: String,
    val birthYear: Int,
    val gender: String,
    val isHavingImg: Boolean,
    val isUsable: Boolean,
    val createdDate: String, // ISO-8601 문자열 형태로 받아서 처리
    val updateDate: String, // ISO-8601 문자열 형태로 받아서 처리
    val lastAccessDate: String?, // nullable
    val memo: String?, // nullable
    val type: String,
    val firstCity: String,
    val secondaryCity: String,
    val thirdCity: String,
    val isAgreeAD: Boolean,
    val isAgreeAlarm: Boolean
)
