package com.desserttime.domain.model

data class WithdrawalData(
    val memberId: String,
    val reasonForLeaving: String,
    val context: String
)
