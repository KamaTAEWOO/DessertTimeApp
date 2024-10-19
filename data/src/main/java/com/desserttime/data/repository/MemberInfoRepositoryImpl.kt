package com.desserttime.data.repository

import com.desserttime.core.local.MemberDataStore
import com.desserttime.data.source.remote.MemberInfoRemoteSource
import com.desserttime.domain.model.MemberData
import com.desserttime.domain.model.RequestInquiryData
import com.desserttime.domain.model.RequestMemberSignUpData
import com.desserttime.domain.model.RequestMyPageMemberSaveData
import com.desserttime.domain.model.ResponseCommon
import com.desserttime.domain.model.ResponseMemberData
import com.desserttime.domain.model.ResponseMyPageMemberData
import com.desserttime.domain.model.ResponseNicknameDoubleCheckData
import com.desserttime.domain.model.ResponseSettingLoadData
import com.desserttime.domain.repository.MemberInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MemberInfoRepositoryImpl @Inject constructor(
    private val memberInfoRemoteSource: MemberInfoRemoteSource,
    private val memberDataStore: MemberDataStore
) : MemberInfoRepository {

    override fun requestMemberSignUp(requestMemberSignUpData: RequestMemberSignUpData): Flow<ResponseCommon> =
        memberInfoRemoteSource.requestMemberSignUp(requestMemberSignUpData)

    override fun requestMemberValidation(snsId: String): Flow<ResponseMemberData> =
        memberInfoRemoteSource.requestMemberValidation(snsId)

    override fun requestInquiry(requestInquiryData: RequestInquiryData): Flow<ResponseCommon> =
        memberInfoRemoteSource.requestInquiry(requestInquiryData)

    override val memberData: Flow<MemberData> = memberDataStore.memberData

    override fun requestMemberData(): Flow<ResponseMyPageMemberData> = memberInfoRemoteSource.requestMemberData()

    override fun requestNicknameDoubleCheck(nickname: String): Flow<ResponseNicknameDoubleCheckData> =
        memberInfoRemoteSource.requestNicknameDoubleCheck(nickname)

    override fun requestMyPageMemberSaveData(requestMyPageMemberSaveData: RequestMyPageMemberSaveData): Flow<ResponseCommon> =
        memberInfoRemoteSource.requestMyPageMemberSaveData(requestMyPageMemberSaveData)

    override fun requestSettingLoadData(memberId: String): Flow<ResponseSettingLoadData> =
        memberInfoRemoteSource.requestSettingLoadData(memberId)

    override fun requestSettingAlarm(memberId: String, isAgreeAlarm: Boolean): Flow<ResponseCommon> =
        memberInfoRemoteSource.requestSettingAlarm(memberId, isAgreeAlarm)

    override fun requestSettingAD(memberId: String, isAgreeAD: Boolean): Flow<ResponseCommon> =
        memberInfoRemoteSource.requestSettingAD(memberId, isAgreeAD)
}
