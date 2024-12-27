package com.desserttime.core.model.dto

import com.desserttime.domain.model.MemberData
import com.desserttime.domain.model.ResponseMemberData

data class ResponseMemberDto(
    val success: Boolean,
    val timestamp: String, // ISO-8601 문자열 형태로 받아서 처리
    val statusCode: Int,
    val message: String,
    val data: MemberData
) {
    // DTO를 도메인 모델로 변환하는 함수
    fun toModel() = ResponseMemberData(
        success = success,
        timestamp = timestamp,
        statusCode = statusCode,
        message = message,
        data = data
    )
}

