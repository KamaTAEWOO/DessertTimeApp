package com.desserttime.domain.model

data class ResponseSettingLoadData(
    val success: Boolean,
    val timestamp: String, // ISO-8601 문자열 형태로 받아서 처리
    val statusCode: Int,
    val message: String,
    val data: SettingLoadData
)

data class SettingLoadData(
    val isAgreeAD: String,
    val isAgreeAlarm: String
)
