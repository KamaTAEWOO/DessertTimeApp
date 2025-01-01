package com.desserttime.home

import com.desserttime.core.base.BaseEvent

sealed class HomeEvent : BaseEvent {

    data class ResponseHomeImageData(
        val homeImageData: String
    ) : HomeEvent()
}
