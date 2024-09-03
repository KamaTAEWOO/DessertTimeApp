package com.desserttime.core.network.service

import com.desserttime.core.model.dto.ResponseMemberSignUpDto
import com.desserttime.core.model.dto.ResponseMemberValidationDto
import com.desserttime.domain.model.RequestMemberSignUpData
import retrofit2.http.Body
import retrofit2.http.POST

interface MemberInfoService {
    @POST("/member/signin")
    suspend fun requestMemberSignUp(
        @Body requestMemberSignUpData: RequestMemberSignUpData
    ): ResponseMemberSignUpDto

    @POST("/member/validation")
    suspend fun requestMemberValidation(
        @Body snsId: String
    ): ResponseMemberValidationDto
}
