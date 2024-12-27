package com.desserttime.data.source.remote

import com.desserttime.core.local.MemberDataStore
import com.desserttime.core.network.service.MemberInfoService
import com.desserttime.domain.model.RequestInquiryData
import com.desserttime.domain.model.RequestMemberSignUpData
import com.desserttime.domain.model.RequestMyPageMemberSaveData
import com.desserttime.domain.model.ResponseMyPageMemberData
import com.desserttime.domain.model.ResponseMyPageNoticeData
import com.desserttime.domain.model.ResponseTokenData
import com.desserttime.domain.model.WithdrawalData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

private const val TAG = "MemberInfoRemoteSource::"

class MemberInfoRemoteSource @Inject constructor(
    private val memberInfoService: MemberInfoService,

    private val memberDataStore: MemberDataStore
) {
    fun requestMemberSignUp(requestMemberSignUpData: RequestMemberSignUpData) = flow {
        emit(memberInfoService.requestMemberSignUp(requestMemberSignUpData).toModel())
    }

    fun requestMemberValidation(snsId: String): Flow<ResponseTokenData> = flow {
        emit(memberInfoService.requestMemberValidation(snsId).toModel())
    }

    fun requestInquiry(requestInquiryData: RequestInquiryData) = flow {
        emit(memberInfoService.requestInquiry(requestInquiryData).toModel())
    }

    fun requestMemberSummaryData(memberId: String): Flow<ResponseMyPageMemberData> = flow {
        emit(memberInfoService.requestMemberSummaryData(memberId).toModel())
    }
    fun requestNicknameDoubleCheck(nickname: String) = flow {
        emit(memberInfoService.requestNicknameDoubleCheck("nickname", nickname).toModel())
    }

    fun requestMyPageMemberSaveData(requestMyPageMemberSaveData: RequestMyPageMemberSaveData) =
        flow {
            emit(
                memberInfoService.requestMyPageMemberSaveData(
                    requestMyPageMemberSaveData.memberId,
                    requestMyPageMemberSaveData.birth,
                    requestMyPageMemberSaveData.gender,
                    requestMyPageMemberSaveData.firstCity,
                    requestMyPageMemberSaveData.secondCity,
                    requestMyPageMemberSaveData.thirdCity,
                    requestMyPageMemberSaveData.nickName
                ).toModel()
            )
        }

    fun requestSettingLoadData(memberId: String) = flow {
        emit(memberInfoService.requestSettingLoadData(memberId).toModel())
    }

    fun requestSettingAlarm(memberId: String, isAgreeAlarm: Boolean) = flow {
        emit(memberInfoService.requestSettingAlarm(memberId, isAgreeAlarm).toModel())
    }

    fun requestSettingAD(memberId: String, isAgreeAD: Boolean) = flow {
        emit(memberInfoService.requestSettingAD(memberId, isAgreeAD).toModel())
    }

    fun requestWithdrawalMember(withdrawalData: WithdrawalData) = flow {
        emit(
            memberInfoService.requestWithdrawalMember(
                withdrawalData.memberId,
                withdrawalData.reasonForLeaving,
                withdrawalData.context
            ).toModel()
        )
    }.onEach {
        runBlocking {
            memberDataStore.clearMemberData()
        }
    }

    fun requestMyPageNoticeData(myPageNoticeData: String): Flow<ResponseMyPageNoticeData> = flow {
        emit(memberInfoService.requestMyPageNoticeData(myPageNoticeData).toModel())
    }

    fun requestLogout() = flow {
        emit(true)
    }.onEach {
        runBlocking {
            memberDataStore.clearMemberData()
        }
    }
}
