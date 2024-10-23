package com.desserttime.domain.repository

import com.desserttime.domain.model.MemberData
import com.desserttime.domain.model.RequestInquiryData
import com.desserttime.domain.model.RequestMemberSignUpData
import com.desserttime.domain.model.RequestMyPageMemberSaveData
import com.desserttime.domain.model.ResponseCommon
import com.desserttime.domain.model.ResponseMemberData
import com.desserttime.domain.model.ResponseMyPageMemberData
import com.desserttime.domain.model.ResponseMyPageNoticeData
import com.desserttime.domain.model.ResponseNicknameDoubleCheckData
import com.desserttime.domain.model.ResponseSettingLoadData
import com.desserttime.domain.model.WithdrawalData
import kotlinx.coroutines.flow.Flow

interface MemberInfoRepository {
    fun requestMemberSignUp(requestMemberSignUpData: RequestMemberSignUpData): Flow<ResponseCommon>

    fun requestMemberValidation(snsId: String): Flow<ResponseMemberData>

    fun requestInquiry(requestInquiryData: RequestInquiryData): Flow<ResponseCommon>

    val memberData: Flow<MemberData> // 로컬 데이터 저장소에서 사용자 정보를 가져오는 Flow

    fun requestMemberData(): Flow<ResponseMyPageMemberData> // 사용자 정보 요청

    fun requestNicknameDoubleCheck(nickname: String): Flow<ResponseNicknameDoubleCheckData> // 닉네임 중복 확인 요청

    fun requestMyPageMemberSaveData(requestMyPageMemberSaveData: RequestMyPageMemberSaveData): Flow<ResponseCommon> // 마이페이지 사용자 정보 저장 요청

    fun requestSettingLoadData(memberId: String): Flow<ResponseSettingLoadData> // 설정 화면 데이터 요청

    fun requestSettingAlarm(memberId: String, isAgreeAlarm: Boolean): Flow<ResponseCommon> // 알림 설정 요청

    fun requestSettingAD(memberId: String, isAgreeAD: Boolean): Flow<ResponseCommon> // 광고 설정 요청

    fun requestWithdrawalMember(withdrawalData: WithdrawalData): Flow<ResponseCommon> // 회원 탈퇴 요청

    fun requestMyPageNoticeData(myPageNoticeData: Boolean): Flow<ResponseMyPageNoticeData> // 마이페이지 공지사항 요청
}
