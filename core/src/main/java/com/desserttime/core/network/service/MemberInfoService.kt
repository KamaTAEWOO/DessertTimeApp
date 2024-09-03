package com.desserttime.core.network.service

import com.desserttime.core.model.dto.ResponseMemberSignUpDto
import com.desserttime.core.model.dto.ResponseMemberValidationDto
import com.desserttime.domain.model.RequestMemberSignUpData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MemberInfoService {
    @POST("/member/signin")
    suspend fun requestMemberSignUp(
        @Body requestMemberSignUpData: RequestMemberSignUpData
    ): ResponseMemberSignUpDto

    @GET("/member/validation/{snsId}")
    suspend fun requestMemberValidation(
        @Path("snsId") snsId: String
    ): ResponseMemberValidationDto
}
