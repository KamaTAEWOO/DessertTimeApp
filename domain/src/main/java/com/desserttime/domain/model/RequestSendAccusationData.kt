package com.desserttime.domain.model

data class RequestSendAccusationData(
    val reason: String,
    val content: String,
    val memberId: Int,
    val reviewId: Int
)
