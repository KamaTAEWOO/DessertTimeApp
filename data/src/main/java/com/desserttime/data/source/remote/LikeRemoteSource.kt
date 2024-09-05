package com.desserttime.data.source.remote

import com.desserttime.core.network.service.LikeService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LikeRemoteSource @Inject constructor(
    private val likeService: LikeService
) {
    fun requestAllAccusations() = flow {
        emit(likeService.requestAllAccusations().toModel())
    }
}