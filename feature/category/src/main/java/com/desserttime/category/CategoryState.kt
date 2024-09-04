package com.desserttime.category

import com.desserttime.core.base.BaseState
import com.desserttime.domain.model.CategoryMainInfoData

data class CategoryState(
    val allCategory: List<CategoryMainInfoData> = listOf()
) : BaseState
