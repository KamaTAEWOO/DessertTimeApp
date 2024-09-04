package com.desserttime.mypage

import com.desserttime.core.base.BaseEvent

sealed class MyPageEvent : BaseEvent {

    data class RequestMyPageData(
        val test: String
    ) : MyPageEvent()
}
