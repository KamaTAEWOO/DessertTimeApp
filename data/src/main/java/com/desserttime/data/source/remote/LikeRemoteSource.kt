package com.desserttime.data.source.remote

import com.desserttime.core.model.dto.RequestSendAccusationDto
import com.desserttime.core.network.service.LikeService
import com.desserttime.domain.model.RequestSendAccusationData
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LikeRemoteSource @Inject constructor(
    private val likeService: LikeService
) {
    fun requestAllAccusations() = flow {
        emit(likeService.requestAllAccusations().toModel())
    }

    fun requestSendAccusation(requestSendAccusationData: RequestSendAccusationData) = flow {
        emit(
            likeService.requestSendAccusation(
                RequestSendAccusationDto(
                    requestSendAccusationData.reason,
                    requestSendAccusationData.content,
                    requestSendAccusationData.memberId,
                    requestSendAccusationData.reviewId
                )
            )
        )
    }
}
