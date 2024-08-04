package com.desserttime.core.model

import com.desserttime.domain.model.CategoryMainInfoModel
import com.desserttime.domain.model.CategorySubInfoModel
import com.google.gson.annotations.SerializedName

data class CategoryDataResponse(
    @SerializedName("data") val responseCategoryData: List<CategoryMainInfoResponse>
)

// Response -> Model
data class CategoryMainInfoResponse(
    val dessertCategoryId: Int = 0,
    val dessertName: String = "",
    val parentDCId: Int = 0,
    val sessionNum: Int = 0,
    val secondCategory: List<CategorySubInfoModel>? = listOf()
) {
    fun toModel() = CategoryMainInfoModel(
        dessertCategoryId = dessertCategoryId,
        dessertName = dessertName,
        parentDCId = parentDCId,
        sessionNum = sessionNum,
        secondCategory = secondCategory
    )
}
