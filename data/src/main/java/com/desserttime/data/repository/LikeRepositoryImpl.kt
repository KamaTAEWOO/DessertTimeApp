package com.desserttime.data.repository

import com.desserttime.data.source.remote.LikeRemoteSource
import com.desserttime.domain.model.ResponseAccusationData
import com.desserttime.domain.repository.LikeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikeRepositoryImpl @Inject constructor(
    private val likeRemoteSource: LikeRemoteSource
) : LikeRepository {

    override fun requestAllAccusations(): Flow<ResponseAccusationData> =
        likeRemoteSource.requestAllAccusations()
}