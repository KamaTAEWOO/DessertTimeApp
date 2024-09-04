package com.desserttime.domain.repository

import com.desserttime.domain.model.CategoryMainInfoData
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    // all categories
    fun requestAllCategories(): Flow<List<CategoryMainInfoData>>
}
