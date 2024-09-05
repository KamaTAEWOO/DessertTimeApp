package com.desserttime.like

import com.desserttime.core.base.BaseEvent

sealed class LikeEvent : BaseEvent {

    data class RequestAccusationData(
        val allAccusations: Map<String, String>
    ) : LikeEvent()
}