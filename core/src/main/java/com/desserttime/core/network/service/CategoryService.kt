package com.desserttime.core.network.service

import com.desserttime.core.model.CategoryDataResponse
import retrofit2.http.GET

interface CategoryService {
    // all categories
    @GET("/dessert-category/all-list")
    suspend fun requestAllCategories(): CategoryDataResponse
}
