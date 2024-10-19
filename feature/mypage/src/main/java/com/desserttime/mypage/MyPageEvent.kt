package com.desserttime.mypage

import com.desserttime.core.base.BaseEvent
import com.desserttime.domain.model.MyPageMemberData
import com.desserttime.domain.model.NickNameDoubleCheckData

sealed class MyPageEvent : BaseEvent {

    data class RequestMyPageMemberData(
        val myPageMemberData: MyPageMemberData
    ) : MyPageEvent()

    data class RequestMyPageNicknameDoubleCheck(
        val isNickNameUsable: NickNameDoubleCheckData
    ) : MyPageEvent()

    data class RequestMyPageSettingLoadData(
        val isAgreeAD: String,
        val isAgreeAlarm: String
    ) : MyPageEvent()
}
