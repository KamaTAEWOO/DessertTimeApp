package com.desserttime.domain.repository

import com.desserttime.domain.model.ResponseCommon
import com.desserttime.domain.model.ReviewWriteData
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {

    // save review
    fun saveReviewWriteData(reviewWriteData: ReviewWriteData) : Flow<ResponseCommon>
}