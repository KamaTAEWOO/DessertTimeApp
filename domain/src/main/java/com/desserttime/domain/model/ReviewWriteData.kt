package com.desserttime.domain.model

data class ReviewWriteData(
    val memberId: Int,
    val reviewId: Int,
    val storeName: String,
    val menuName: String,
    val dessertCategoryId: Int,
    val score: Int,
    val ingredientId: List<Int>,
    val content: String,
    val isSaved: Boolean
)
