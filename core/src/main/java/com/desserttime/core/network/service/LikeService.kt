package com.desserttime.core.network.service

import com.desserttime.core.model.dto.ResponseAccusationDto
import retrofit2.http.GET

interface LikeService {
    // all accusations
    @GET("/accusation")
    suspend fun requestAllAccusations(): ResponseAccusationDto
}