package com.desserttime.core.network.service

import com.desserttime.core.model.dto.ResponseCommonDto
import com.desserttime.core.model.dto.ResponseMemberValidationDto
import com.desserttime.core.model.dto.ResponseMyPageMemberDto
import com.desserttime.core.model.dto.ResponseMyPageNicknameDoubleCheckDto
import com.desserttime.domain.model.RequestInquiryData
import com.desserttime.domain.model.RequestMemberSignUpData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("/member/my-page/nickname/{nickname}")
    suspend fun requestNicknameDoubleCheck(
        @Path("nickname") nickname: String, // URL 경로에 있는 nickname
        @Query("nickName") nickName: String // 쿼리 파라미터로 전달되는 nickName
    ): ResponseMyPageNicknameDoubleCheckDto

    @PATCH("/member/my-page")
    suspend fun requestMyPageMemberSaveData(
        @Query("memberId") requestMyPageMemberSaveData: String,
        @Query("birthYear") birth: String,
        @Query("gender") gender: String,
        @Query("firstCity") firstCity: String,
        @Query("secondaryCity") secondCity: String,
        @Query("thirdCity") thirdCity: String,
        @Query("nickName") nickName: String
    ): ResponseCommonDto
}
