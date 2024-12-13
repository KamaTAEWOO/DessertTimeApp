package com.desserttime.mypage

import com.desserttime.core.base.BaseEvent
import com.desserttime.domain.model.MyPageMemberData
import com.desserttime.domain.model.ResponseMyPageNoticeData
import com.desserttime.mypage.model.NickNameDoubleCheckData

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

    data class RequestMyPageNoticeData(
        val noticeArrayData: List<ResponseMyPageNoticeData.NoticeData.Notice>
    ) : MyPageEvent()

    data class RequestMyPageEventData(
        val eventArrayData: List<ResponseMyPageNoticeData.NoticeData.Notice>
    ) : MyPageEvent()

    data class RequestMyPageFQAData(
        val fqaArrayData: List<ResponseMyPageNoticeData.NoticeData.Notice>
    ) : MyPageEvent()
}
