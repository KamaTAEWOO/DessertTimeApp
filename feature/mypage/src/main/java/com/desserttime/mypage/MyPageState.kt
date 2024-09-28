package com.desserttime.mypage

import com.desserttime.core.base.BaseState

data class MyPageState(
    var taste: String = "",
    var isNoticeAndEvent: Boolean = false
) : BaseState
