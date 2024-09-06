package com.desserttime.like

import com.desserttime.core.base.BaseState

data class LikeState(
    val allAccusations: Map<String, String> = mapOf()
) : BaseState
