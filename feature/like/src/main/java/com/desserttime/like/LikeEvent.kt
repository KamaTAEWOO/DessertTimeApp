package com.desserttime.like

import com.desserttime.core.base.BaseEvent

sealed class LikeEvent : BaseEvent {

    data class RequestCategoryData(
        val test:  String
    ) : LikeEvent()
}