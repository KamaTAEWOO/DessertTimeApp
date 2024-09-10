package com.desserttime.core.model.dto

import com.google.gson.annotations.SerializedName

data class RequestSendAccusationDto(
    @SerializedName("reason") val reason: String,
    @SerializedName("content") val content: String,
    @SerializedName("memberId") val memberId: Int,
    @SerializedName("reviewId") val reviewId: Int
)
