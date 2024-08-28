package com.desserttime.data.source.remote

import com.desserttime.core.network.service.UserInfoService
import com.desserttime.domain.model.RequestUserSignUp
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserInfoRemoteSource @Inject constructor(
    private val userInfoService: UserInfoService
) {
    fun requestUserSignUp(requestUserSignUp: RequestUserSignUp) = flow {
        emit( userInfoService.requestUserSignUp(requestUserSignUp).toModel())
    }
}