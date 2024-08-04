package com.desserttime.domain.model

data class CategoryMainInfoModel (
    val dessertCategoryId: Int = 0,
    val dessertName: String = "",
    val parentDCId: Int = 0,
    val sessionNum: Int = 0,
    val secondCategory: List<CategorySubInfoModel>? = listOf()
)

data class CategorySubInfoModel(
    val dessertCategoryId: Int = 0,
    val dessertName: String = "",
    val parentDCId: Int = 0,
    val sessionNum: Int = 0,
    val secondCategory: List<String>? = listOf() // 추후에 추가 및 변경 예정, 현재 비어있음.
)
