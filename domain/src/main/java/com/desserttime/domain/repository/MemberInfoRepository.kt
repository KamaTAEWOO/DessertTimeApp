package com.desserttime.domain.repository

import com.desserttime.domain.model.MemberData
import com.desserttime.domain.model.RequestInquiryData
import com.desserttime.domain.model.RequestMemberSignUpData
import com.desserttime.domain.model.ResponseMemberData
import com.desserttime.domain.model.ResponseCommon
import kotlinx.coroutines.flow.Flow

interface MemberInfoRepository {
    fun requestMemberSignUp(requestMemberSignUpData: RequestMemberSignUpData): Flow<ResponseCommon>

    fun requestMemberValidation(snsId: String): Flow<ResponseMemberData>

    fun requestInquiry(requestInquiryData: RequestInquiryData): Flow<ResponseCommon>

    val memberData: Flow<MemberData> // 로컬 데이터 저장소에서 사용자 정보를 가져오는 Flow
}
