package com.desserttime.core.model.dto

import com.desserttime.domain.model.DessertCategory
import com.desserttime.domain.model.ResponseHomeImageData

data class ResponseHomeImageDto(
    val success: Boolean,
    val timestamp: String,
    val statusCode: Int,
    val message: String,
    val data: List<DessertCategory>
) {
    fun toModel() = ResponseHomeImageData(
        success = success,
        timestamp = timestamp,
        statusCode = statusCode,
        message = message,
        data = data
    )
}
