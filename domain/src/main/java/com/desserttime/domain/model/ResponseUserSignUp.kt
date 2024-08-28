package com.desserttime.domain.model

data class ResponseUserSignUp(
    val success: Boolean = false,
    val timestamp: String = "",
    val statusCode: Int = 0,
    val message: String = ""
)
