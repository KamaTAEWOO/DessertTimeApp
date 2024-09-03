package com.desserttime.core.model.dto

import com.desserttime.domain.model.CategoryMainInfoModel
import com.desserttime.domain.model.CategorySubInfoModel
import com.google.gson.annotations.SerializedName

data class ResponseCategoryDto(
    @SerializedName("data") val categoryMainInfoList: List<ResponseCategoryMainInfo>
)

// Response -> Model 변환 데이터 클래스
data class ResponseCategoryMainInfo(
    @SerializedName("dessertCategoryId") val dessertCategoryId: Int,
    @SerializedName("dessertName") val dessertName: String,
    @SerializedName("parentDCId") val parentDCId: Int,
    @SerializedName("sessionNum") val sessionNum: Int,
    @SerializedName("secondCategory") val secondCategory: List<CategorySubInfoModel>?
) {
    // Model 변환 함수
    fun toModel() = CategoryMainInfoModel(
        dessertCategoryId = dessertCategoryId,
        dessertName = dessertName,
        parentDCId = parentDCId,
        sessionNum = sessionNum,
        secondCategory = secondCategory ?: emptyList() // null이면 빈 리스트 반환
    )
}