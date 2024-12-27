package com.desserttime.core.utility

import com.desserttime.domain.model.TokenData

object MemberDataManager {
    var token: String? = null
    var memberId: Int? = 0
    var nickName: String? = null

    // save data
    fun saveData(data: TokenData) {
        token = data.token
        memberId = data.memberId
        nickName = data.nickName
    }
}