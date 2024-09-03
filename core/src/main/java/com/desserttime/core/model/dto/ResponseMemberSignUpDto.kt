package com.desserttime.core.model.dto

import com.desserttime.domain.model.ResponseMemberSignUp
import com.google.gson.annotations.SerializedName

// 통합된 Response 데이터 클래스
data class ResponseMemberSignUpDto(
    @SerializedName("success") val success: Boolean,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String
) {
    // Model로 변환하는 함수
    fun toModel() = ResponseMemberSignUp(
        success = success,
        timestamp = timestamp,
        statusCode = statusCode,
        message = message
    )
}
