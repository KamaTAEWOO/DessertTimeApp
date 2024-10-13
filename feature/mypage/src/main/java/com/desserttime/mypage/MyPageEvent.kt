package com.desserttime.mypage

import com.desserttime.core.base.BaseEvent
import com.desserttime.domain.model.MyPageMemberData

sealed class MyPageEvent : BaseEvent {

    data class RequestMyPageMemberData(
        val myPageMemberData: MyPageMemberData
    ) : MyPageEvent()
}
