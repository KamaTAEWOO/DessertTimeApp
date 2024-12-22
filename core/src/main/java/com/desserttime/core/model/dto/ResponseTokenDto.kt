package com.desserttime.core.model.dto

import com.desserttime.domain.model.ResponseTokenData
import com.desserttime.domain.model.TokenData

data class ResponseTokenDto(
    val success: Boolean,
    val timestamp: String,
    val statusCode: Int,
    val message: String,
    val data: TokenData
) {
    fun toModel() = ResponseTokenData(
        success = success,
        timestamp = timestamp,
        statusCode = statusCode,
        message = message,
        data = data
    )
}
