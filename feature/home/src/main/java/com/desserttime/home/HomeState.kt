package com.desserttime.home

import com.desserttime.core.base.BaseState

data class HomeState(
    val reviewImageData: List<String> = emptyList<String>()
) : BaseState
