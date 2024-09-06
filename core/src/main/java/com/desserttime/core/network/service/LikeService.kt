package com.desserttime.core.network.service

import com.desserttime.core.model.dto.RequestSendAccusationDto
import com.desserttime.core.model.dto.ResponseAccusationDto
import com.desserttime.domain.model.ResponseCommon
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LikeService {
    // all accusations
    @GET("/accusation")
    suspend fun requestAllAccusations(): ResponseAccusationDto

    @POST("/accusation")
    suspend fun requestSendAccusation(
        @Body requestSendAccusationDto: RequestSendAccusationDto
    ): ResponseCommon
}