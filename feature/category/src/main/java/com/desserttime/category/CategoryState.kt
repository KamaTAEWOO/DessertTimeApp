package com.desserttime.category

import com.desserttime.core.base.BaseState
import com.desserttime.domain.model.CategoryMainInfoModel

data class CategoryState(
    val allCategory: List<CategoryMainInfoModel> = listOf()
) : BaseState
