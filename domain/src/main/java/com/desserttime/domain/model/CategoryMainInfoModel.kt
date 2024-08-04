package com.desserttime.domain.model

data class CategoryMainInfoModel (
    val dessertCategoryId: Int = 0,
    val dessertName: String = "",
    val parentDCId: Int = 0,
    val sessionNum: Int = 0,
    val secondCategory: List<CategorySubInfoModel> = listOf()
)