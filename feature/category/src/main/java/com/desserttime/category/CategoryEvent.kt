package com.desserttime.category

import com.desserttime.core.base.BaseEvent
import com.desserttime.domain.model.CategoryMainInfoModel

sealed class CategoryEvent : BaseEvent {

    data class RequestCategoryData(
        val allCategory: List<CategoryMainInfoModel>
    ) : CategoryEvent()
}
