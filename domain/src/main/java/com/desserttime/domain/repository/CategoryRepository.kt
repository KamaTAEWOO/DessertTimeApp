package com.desserttime.domain.repository

import com.desserttime.domain.model.CategoryMainInfoModel
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    // all categories
    fun requestAllCategories(): Flow<List<CategoryMainInfoModel>>
}
