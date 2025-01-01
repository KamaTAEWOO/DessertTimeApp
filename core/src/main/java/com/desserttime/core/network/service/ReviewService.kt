package com.desserttime.core.network.service

import com.desserttime.core.model.dto.ResponseCommonDto
import com.desserttime.core.model.dto.ResponseHomeImageDto
import com.desserttime.domain.model.ReviewWriteData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface ReviewService {

    @GET("/review/home/list/{memberId}")
    suspend fun requestHomeImageData(
        @Path("memberId") memberId: Int
    ): ResponseHomeImageDto

    @PATCH("/review/generable")
    suspend fun saveReviewWriteData(
        @Body requestReviewWriteData: ReviewWriteData
    ): ResponseCommonDto
}
