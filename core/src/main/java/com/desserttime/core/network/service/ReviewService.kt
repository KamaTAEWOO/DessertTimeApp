package com.desserttime.core.network.service

import com.desserttime.core.model.dto.ResponseCommonDto
import com.desserttime.domain.model.ReviewWriteData
import retrofit2.http.Body
import retrofit2.http.PATCH

interface ReviewService {

    @PATCH("/review/generable")
    suspend fun saveReviewWriteData(
        @Body requestReviewWriteData: ReviewWriteData
    ): ResponseCommonDto
}