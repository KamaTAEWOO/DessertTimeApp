package com.desserttime.domain.model

data class ResponseMyPageNoticeData(
    val success: Boolean,
    val timestamp: String,
    val statusCode: Int,
    val message: String,
    val data: NoticeData
) {
    data class NoticeData(
        val items: List<Notice>,
        val hasNextPage: Boolean,
        val nextCursor: String?
    ) {
        data class Notice(
            val noticeId: Int,
            val title: String,
            val createdDate: String
        )
    }
}
