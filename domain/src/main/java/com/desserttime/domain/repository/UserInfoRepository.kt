package com.desserttime.domain.repository

import com.desserttime.domain.model.RequestUserSignUp
import com.desserttime.domain.model.ResponseUserSignUp
import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {
    fun requestUserSignUp(requestUserSignUp: RequestUserSignUp): Flow<ResponseUserSignUp>
}
