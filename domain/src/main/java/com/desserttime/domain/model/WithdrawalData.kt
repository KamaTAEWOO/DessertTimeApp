package com.desserttime.domain.model

data class WithdrawalData(
    val memberId: Int,
    val reasonForLeaving: String,
    val context: String
)
