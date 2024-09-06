package com.desserttime.core.model.dto

import com.desserttime.domain.model.ResponseAccusationData
import com.google.gson.annotations.SerializedName

// Response -> Model 변환 데이터 클래스
data class ResponseAccusationDto(
    @SerializedName("success") val success: Boolean,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Map<String, String>
) {
    // Model 변환 함수
    fun toModel() = ResponseAccusationData(
        success = success,
        timestamp = timestamp,
        statusCode = statusCode,
        message = message,
        data = data
    )
}
