package com.desserttime.data.repository

import com.desserttime.data.source.remote.UserInfoRemoteSource
import com.desserttime.domain.model.RequestUserSignUp
import com.desserttime.domain.model.ResponseUserSignUp
import com.desserttime.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val userInfoRemoteSource: UserInfoRemoteSource
) : UserInfoRepository {
    override fun requestUserSignUp(requestUserSignUp: RequestUserSignUp): Flow<ResponseUserSignUp> =
        userInfoRemoteSource.requestUserSignUp(requestUserSignUp)
}
