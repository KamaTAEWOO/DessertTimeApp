package com.desserttime.category

import com.desserttime.core.base.BaseState
import com.desserttime.domain.model.ResponseCategoryMainInfoData

data class CategoryState(
    val allCategory: List<ResponseCategoryMainInfoData> = listOf()
) : BaseState
