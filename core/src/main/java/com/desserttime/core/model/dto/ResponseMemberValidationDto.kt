package com.desserttime.core.model.dto

import com.desserttime.domain.model.MemberData
import com.desserttime.domain.model.ResponseMemberData
import com.google.gson.annotations.SerializedName

data class ResponseMemberValidationDto(
    @SerializedName("isValid") val isValid: Boolean,
    @SerializedName("timestamp") val timestamp: String, // ISO-8601 문자열 형태로 받아서 처리
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: MemberData
) {
    // DTO를 도메인 모델로 변환하는 함수
    fun toModel() = ResponseMemberData(
        success = isValid,
        timestamp = timestamp,
        statusCode = statusCode,
        message = message,
        data = data
    )
}