package com.desserttime.core.model.dto

import com.desserttime.domain.model.ResponseMyPageNoticeData
import com.google.gson.annotations.SerializedName

data class ResponseMyPageNoticeDto(
    @SerializedName("success") val success: Boolean,
    @SerializedName("timestamp") val timestamp: String,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: DataDto?
) {
    fun toModel() = ResponseMyPageNoticeData(
        success = success,
        timestamp = timestamp,
        statusCode = statusCode,
        message = message,
        data = data?.toModel() ?: ResponseMyPageNoticeData.NoticeData(emptyList(), false, null)
    )

    data class DataDto(
        @SerializedName("items") val items: List<NoticeDto> = listOf(),
        @SerializedName("hasNextPage") val hasNextPage: Boolean,
        @SerializedName("nextCursor") val nextCursor: String?
    ) {
        fun toModel() = ResponseMyPageNoticeData.NoticeData(
            items = items.map { it.toModel() },
            hasNextPage = hasNextPage,
            nextCursor = nextCursor
        )
    }

    data class NoticeDto(
        @SerializedName("noticeId") val noticeId: Int,
        @SerializedName("title") val title: String,
        @SerializedName("createdDate") val createdDate: String
    ) {
        fun toModel() = ResponseMyPageNoticeData.NoticeData.Notice(
            noticeId = noticeId,
            title = title,
            content = createdDate
        )
    }
}

