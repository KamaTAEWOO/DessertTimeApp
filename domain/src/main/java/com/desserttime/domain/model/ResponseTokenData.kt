package com.desserttime.domain.model

data class ResponseTokenData(
    val success: Boolean,
    val timestamp: String,
    val statusCode: Int,
    val message: String,
    val data: TokenData
)

data class TokenData(
    val token: String
)
