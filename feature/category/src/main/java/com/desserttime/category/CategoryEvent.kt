package com.desserttime.category

import com.desserttime.core.base.BaseEvent
import com.desserttime.domain.model.CategoryMainInfoData

sealed class CategoryEvent : BaseEvent {

    data class RequestCategoryData(
        val allCategory: List<CategoryMainInfoData>
    ) : CategoryEvent()
}
