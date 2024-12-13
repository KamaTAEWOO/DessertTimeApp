package com.desserttime.mypage

import com.desserttime.core.base.BaseState
import com.desserttime.domain.model.MyPageMemberData
import com.desserttime.domain.model.ResponseMyPageNoticeData
import com.desserttime.mypage.model.NickNameDoubleCheckData

data class MyPageState(
    var taste: String = "",
    var isNoticeAndEvent: Boolean = false,
    var myPageMemberData: MyPageMemberData = MyPageMemberData("", 0, 0),
    var isNickNameUsable: NickNameDoubleCheckData = NickNameDoubleCheckData.NONE,
    var isAgreeAD: String = "",
    var isAgreeAlarm: String = "",
    var noticeArrayData: List<ResponseMyPageNoticeData.NoticeData.Notice> = emptyList(),
    var eventArrayData: List<ResponseMyPageNoticeData.NoticeData.Notice> = emptyList(),
    var fqaArrayData: List<ResponseMyPageNoticeData.NoticeData.Notice> = emptyList()
) : BaseState
