package com.desserttime.category

import com.desserttime.core.base.BaseEvent
import com.desserttime.domain.model.ResponseCategoryMainInfoData

sealed class CategoryEvent : BaseEvent {

    data class RequestCategoryData(
        val allCategory: List<ResponseCategoryMainInfoData>
    ) : CategoryEvent()
}
