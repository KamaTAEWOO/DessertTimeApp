package com.desserttime.domain.repository

import com.desserttime.domain.model.ResponseAccusationData
import kotlinx.coroutines.flow.Flow

interface LikeRepository {

    // 신고하기
    fun requestAllAccusations(): Flow<ResponseAccusationData>
}