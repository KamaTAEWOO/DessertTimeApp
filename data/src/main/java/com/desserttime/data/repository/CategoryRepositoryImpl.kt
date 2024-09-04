package com.desserttime.data.repository

import com.desserttime.data.source.remote.CategoryRemoteSource
import com.desserttime.domain.model.ResponseCategoryMainInfoData
import com.desserttime.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryRemoteSource: CategoryRemoteSource
) : CategoryRepository {

    override fun requestAllCategories(): Flow<List<ResponseCategoryMainInfoData>> =
        categoryRemoteSource.requestAllCategories()
}
