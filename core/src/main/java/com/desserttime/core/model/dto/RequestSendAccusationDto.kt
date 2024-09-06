package com.desserttime.core.model.dto

data class RequestSendAccusationDto (
    val reason: String,
    val content: String,
    val memberId: Int,
    val reviewId: Int
)