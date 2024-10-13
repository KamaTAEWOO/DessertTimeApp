package com.desserttime.core.model.dto

import com.desserttime.domain.model.MyPageMemberData
import com.desserttime.domain.model.ResponseMyPageMemberData
import com.google.gson.annotations.SerializedName

data class ResponseMyPageMemberDto(
    @SerializedName("isValid") val isValid: Boolean,
    @SerializedName("timestamp") val timestamp: String, // ISO-8601 문자열 형태로 받아서 처리
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: MyPageMemberData
) {
    // DTO를 도메인 모델로 변환하는 함수
    fun toModel() = ResponseMyPageMemberData(
        success = isValid,
        timestamp = timestamp,
        statusCode = statusCode,
        message = message,
        data = data
    )
}
