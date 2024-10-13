package com.desserttime.core.network.service

import com.desserttime.core.model.dto.ResponseCommonDto
import com.desserttime.core.model.dto.ResponseMemberValidationDto
import com.desserttime.core.model.dto.ResponseMyPageMemberDto
import com.desserttime.domain.model.RequestInquiryData
import com.desserttime.domain.model.RequestMemberSignUpData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MemberInfoService {
    @POST("/member/signin")
    suspend fun requestMemberSignUp(
        @Body requestMemberSignUpData: RequestMemberSignUpData
    ): ResponseCommonDto

    @GET("/member/validation/{snsId}")
    suspend fun requestMemberValidation(
        @Path("snsId") snsId: String
    ): ResponseMemberValidationDto

    @POST("/qna")
    suspend fun requestInquiry(
        @Body requestInquiryData: RequestInquiryData
    ): ResponseCommonDto

    // My Page
    @GET("/member/my-page/{memberId}")
    suspend fun requestMemberData(
        @Path("memberId") memberId: String
    ): ResponseMyPageMemberDto
}
