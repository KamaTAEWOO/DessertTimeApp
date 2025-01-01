package com.desserttime.domain.repository

import com.desserttime.domain.model.ResponseHomeImageData
import com.desserttime.domain.model.ResponseCommon
import com.desserttime.domain.model.ReviewWriteData
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {

    // home image
    fun requestHomeImageData(memberId: Int): Flow<ResponseHomeImageData>

    // save review
    fun saveReviewWriteData(reviewWriteData: ReviewWriteData): Flow<ResponseCommon>
}
