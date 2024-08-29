package com.desserttime.core.network.service

import com.desserttime.core.model.reponse.ResponseUserInfoSignUp
import com.desserttime.domain.model.RequestUserSignUp
import retrofit2.http.Body
import retrofit2.http.POST

interface UserInfoService {
    @POST("/member/signin")
    suspend fun requestUserSignUp(
        @Body requestUserSignUp: RequestUserSignUp
    ): ResponseUserInfoSignUp
}
