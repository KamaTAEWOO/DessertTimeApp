package com.desserttime.data.repository

import com.desserttime.data.source.remote.MemberInfoRemoteSource
import com.desserttime.domain.model.RequestInquiryData
import com.desserttime.domain.model.RequestMemberSignUpData
import com.desserttime.domain.model.ResponseMemberData
import com.desserttime.domain.model.ResponseCommon
import com.desserttime.domain.repository.MemberInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberInfoRepositoryImpl @Inject constructor(
    private val memberInfoRemoteSource: MemberInfoRemoteSource
) : MemberInfoRepository {
    override fun requestMemberSignUp(requestMemberSignUpData: RequestMemberSignUpData): Flow<ResponseCommon> =
        memberInfoRemoteSource.requestMemberSignUp(requestMemberSignUpData)

    override fun requestMemberValidation(snsId: String): Flow<ResponseMemberData> =
        memberInfoRemoteSource.requestMemberValidation(snsId)

    override fun requestInquiry(requestInquiryData: RequestInquiryData): Flow<ResponseCommon> =
        memberInfoRemoteSource.requestInquiry(requestInquiryData)
}
