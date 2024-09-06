package com.desserttime.domain.repository

import com.desserttime.domain.model.RequestSendAccusationData
import com.desserttime.domain.model.ResponseAccusationData
import com.desserttime.domain.model.ResponseCommon
import kotlinx.coroutines.flow.Flow

interface LikeRepository {

    // 신고하기
    fun requestAllAccusations(): Flow<ResponseAccusationData>

    // 신고 데이터 보내기
    fun requestSendAccusation(requestSendAccusationData: RequestSendAccusationData): Flow<ResponseCommon>
}
