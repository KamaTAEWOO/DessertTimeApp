package com.desserttime.data.source.remote

import com.desserttime.core.network.service.MemberInfoService
import com.desserttime.domain.model.RequestMemberSignUpData
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MemberInfoRemoteSource @Inject constructor(
    private val memberInfoService: MemberInfoService
) {
    fun requestMemberSignUp(requestMemberSignUpData: RequestMemberSignUpData) = flow {
        emit(memberInfoService.requestMemberSignUp(requestMemberSignUpData).toModel())
    }

    fun requestMemberValidation(snsId: String) = flow {
        emit(memberInfoService.requestMemberValidation(snsId).toModel())
    }
}
