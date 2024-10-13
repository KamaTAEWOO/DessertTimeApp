package com.desserttime.domain.repository

import com.desserttime.domain.model.MemberData
import com.desserttime.domain.model.RequestInquiryData
import com.desserttime.domain.model.RequestMemberSignUpData
import com.desserttime.domain.model.ResponseCommon
import com.desserttime.domain.model.ResponseMemberData
import com.desserttime.domain.model.ResponseMyPageMemberData
import com.desserttime.domain.model.ResponseNicknameDoubleCheckData
import kotlinx.coroutines.flow.Flow

interface MemberInfoRepository {
    fun requestMemberSignUp(requestMemberSignUpData: RequestMemberSignUpData): Flow<ResponseCommon>

    fun requestMemberValidation(snsId: String): Flow<ResponseMemberData>

    fun requestInquiry(requestInquiryData: RequestInquiryData): Flow<ResponseCommon>

    val memberData: Flow<MemberData> // 로컬 데이터 저장소에서 사용자 정보를 가져오는 Flow

    fun requestMemberData(): Flow<ResponseMyPageMemberData> // 사용자 정보 요청

    fun requestNicknameDoubleCheck(nickname: String): Flow<ResponseNicknameDoubleCheckData> // 닉네임 중복 확인 요청
}
