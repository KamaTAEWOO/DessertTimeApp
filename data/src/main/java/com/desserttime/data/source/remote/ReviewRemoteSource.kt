package com.desserttime.data.source.remote

import com.desserttime.core.network.service.ReviewService
import com.desserttime.domain.model.ReviewWriteData
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReviewRemoteSource @Inject constructor(
    private val reviewService: ReviewService
) {
    fun saveReviewWriteData(reviewWriteData: ReviewWriteData) = flow {
        emit(
            reviewService.saveReviewWriteData(reviewWriteData).toModel()
        )
    }
}