package com.desserttime.domain.model

data class ResponseAccusationData(
    val success: Boolean,
    val timestamp: String,
    val statusCode: Int,
    val message: String,
    val data: Map<String, String>
)
