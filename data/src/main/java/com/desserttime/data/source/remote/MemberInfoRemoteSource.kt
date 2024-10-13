package com.desserttime.data.source.remote

import com.desserttime.core.local.MemberDataStore
import com.desserttime.core.network.service.MemberInfoService
import com.desserttime.domain.model.RequestInquiryData
import com.desserttime.domain.model.RequestMemberSignUpData
import com.desserttime.domain.model.ResponseMemberData
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

    fun requestMemberValidation(snsId: String): Flow<ResponseMemberData> = flow {
        // 서버에서 데이터를 받아오고 `Flow`로 방출
        val response = memberInfoService.requestMemberValidation(snsId).toModel()
        emit(response) // `Flow`로 데이터를 방출
    }.onEach { response ->
        runBlocking {
            memberDataStore.saveMemberData(response.data)
        }
    }

    fun requestInquiry(requestInquiryData: RequestInquiryData) = flow {
        emit(memberInfoService.requestInquiry(requestInquiryData).toModel())
    }

    fun requestMemberData() = flow {
        emit(memberInfoService.requestMemberData("1").toModel())
    }
    fun requestNicknameDoubleCheck(nickname: String) = flow {
        emit(memberInfoService.requestNicknameDoubleCheck("nickname", nickname).toModel())
    }
}
