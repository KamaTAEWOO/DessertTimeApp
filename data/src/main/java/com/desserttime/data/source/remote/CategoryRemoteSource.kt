package com.desserttime.data.source.remote

import com.desserttime.core.network.service.CategoryService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRemoteSource @Inject constructor(
    private val categoryService: CategoryService
) {
    fun requestAllCategories() = flow {
        emit(categoryService.requestAllCategories().categoryMainInfoList.map { it.toModel() })
    }
}
