package com.desserttime.core.model.dto

import com.desserttime.domain.model.NoticeData
import com.desserttime.domain.model.ResponseMyPageNoticeData
import com.google.gson.annotations.SerializedName

data class ResponseMyPageNoticeDto(
    @SerializedName("success") val success: Boolean,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<NoticeData>? = listOf()
) {
    fun toModel() = ResponseMyPageNoticeData(
        success = success,
        timestamp = timestamp,
        statusCode = statusCode,
        message = message,
        data = data
    )
}
