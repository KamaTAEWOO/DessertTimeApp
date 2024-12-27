package com.desserttime.domain.model

data class ResponseTokenData(
    val success: Boolean,
    val timestamp: String,
    val statusCode: Int,
    val message: String,
    val data: TokenData
)

data class TokenData(
    val memberId: Int,
    val nickName: String,
    val token: String
)
