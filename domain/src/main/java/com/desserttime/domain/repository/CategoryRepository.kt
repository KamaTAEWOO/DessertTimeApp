package com.desserttime.domain.repository

import com.desserttime.domain.model.ResponseCategoryMainInfoData
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    // all categories
    fun requestAllCategories(): Flow<List<ResponseCategoryMainInfoData>>
}
