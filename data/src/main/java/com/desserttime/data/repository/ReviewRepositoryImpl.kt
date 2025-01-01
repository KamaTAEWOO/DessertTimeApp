package com.desserttime.data.repository

import com.desserttime.data.source.remote.ReviewRemoteSource
import com.desserttime.domain.model.ResponseHomeImageData
import com.desserttime.domain.model.ResponseCommon
import com.desserttime.domain.model.ReviewWriteData
import com.desserttime.domain.repository.ReviewRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(
    private val reviewRemoteSource: ReviewRemoteSource
) : ReviewRepository {

    override fun requestHomeImageData(memberId: Int): Flow<ResponseHomeImageData> =
        reviewRemoteSource.requestHomeImageData(memberId)

    override fun saveReviewWriteData(reviewWriteData: ReviewWriteData): Flow<ResponseCommon> =
        reviewRemoteSource.saveReviewWriteData(reviewWriteData)
}
