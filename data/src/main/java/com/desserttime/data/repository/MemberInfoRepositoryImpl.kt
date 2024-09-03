package com.desserttime.data.repository

import com.desserttime.data.source.remote.MemberInfoRemoteSource
import com.desserttime.domain.model.RequestMemberSignUpData
import com.desserttime.domain.model.ResponseMemberData
import com.desserttime.domain.model.ResponseMemberSignUp
import com.desserttime.domain.repository.MemberInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberInfoRepositoryImpl @Inject constructor(
    private val memberInfoRemoteSource: MemberInfoRemoteSource
) : MemberInfoRepository {
    override fun requestMemberSignUp(requestMemberSignUpData: RequestMemberSignUpData): Flow<ResponseMemberSignUp> =
        memberInfoRemoteSource.requestMemberSignUp(requestMemberSignUpData)

    override fun requestMemberValidation(snsId: String): Flow<ResponseMemberData> =
        memberInfoRemoteSource.requestMemberValidation(snsId)
}
