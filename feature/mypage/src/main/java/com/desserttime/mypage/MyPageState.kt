package com.desserttime.mypage

import com.desserttime.core.base.BaseState
import com.desserttime.domain.model.MyPageMemberData

data class MyPageState(
    var taste: String = "",
    var isNoticeAndEvent: Boolean = false,
    var myPageMemberData: MyPageMemberData = MyPageMemberData("", 0, 0)
) : BaseState
