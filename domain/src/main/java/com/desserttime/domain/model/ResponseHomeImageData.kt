package com.desserttime.domain.model

data class ResponseHomeImageData(
    val success: Boolean,
    val timestamp: String,
    val statusCode: Int,
    val message: String,
    val data: List<DessertCategory>
)

data class DessertCategory(
    val dessertCategoryId: Int,
    val dessertName: String,
    val categoryReviewImgList: List<CategoryReviewImage>
)

data class CategoryReviewImage(
    val reviewId: Int,
    val middlepath: String,
    val path: String,
    val extention: String,
    val imgName: String
)
