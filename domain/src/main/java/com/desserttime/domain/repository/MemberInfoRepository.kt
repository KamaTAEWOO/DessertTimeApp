package com.desserttime.domain.repository

import com.desserttime.domain.model.RequestMemberSignUpData
import com.desserttime.domain.model.ResponseMemberData
import com.desserttime.domain.model.ResponseMemberSignUp
import kotlinx.coroutines.flow.Flow

interface MemberInfoRepository {
    fun requestMemberSignUp(requestMemberSignUpData: RequestMemberSignUpData): Flow<ResponseMemberSignUp>

    fun requestMemberValidation(snsId: String): Flow<ResponseMemberData>
}
