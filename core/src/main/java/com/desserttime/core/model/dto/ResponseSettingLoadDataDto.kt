package com.desserttime.core.model.dto

import com.desserttime.domain.model.ResponseSettingLoadData
import com.desserttime.domain.model.SettingLoadData
import com.google.gson.annotations.SerializedName

data class ResponseSettingLoadDataDto(
    @SerializedName("success") val success: Boolean,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: SettingLoadData
) {
    fun toModel() = ResponseSettingLoadData(
        success = success,
        timestamp = timestamp,
        statusCode = statusCode,
        message = message,
        data = data
    )
}
