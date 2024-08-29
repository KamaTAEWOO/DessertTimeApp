package com.desserttime.core.model.reponse

import com.desserttime.domain.model.ResponseUserSignUp
import com.google.gson.annotations.SerializedName

data class ResponseUserDataSignup(
    @SerializedName("success") val success: Boolean = false,
    @SerializedName("timestamp") val timestamp: String = "",
    @SerializedName("statusCode") val statusCode: Int = 0,
    @SerializedName("message") val message: String = ""
)

// Response -> Model
data class ResponseUserInfoSignUp(
    val success: Boolean = false,
    val timestamp: String = "",
    val statusCode: Int = 0,
    val message: String = ""
) {
    fun toModel() = ResponseUserSignUp(
        success = success,
        timestamp = timestamp,
        statusCode = statusCode,
        message = message
    )
}
