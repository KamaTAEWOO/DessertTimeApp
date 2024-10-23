package com.desserttime.domain.model

data class ResponseMyPageNoticeData(
    val success: Boolean,
    val timestamp: String, // ISO-8601 문자열 형태로 받아서 처리
    val statusCode: Int,
    val message: String,
    val data: List<NoticeData>? = listOf()
)

data class NoticeData(
    val noticeId: Int,
    val title: String,
    val createdDate: String
)
