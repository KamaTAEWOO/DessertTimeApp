package com.desserttime.model

import com.desserttime.category.CategoryViewModel
import com.desserttime.home.HomeViewModel
import com.desserttime.like.LikeViewModel
import com.desserttime.mypage.MyPageViewModel
import com.desserttime.review.ReviewViewModel

data class ViewModels(
    val categoryViewModel: CategoryViewModel,
    val reviewViewModel: ReviewViewModel,
    val myPageViewModel: MyPageViewModel,
    val homeViewModel: HomeViewModel,
    val likeViewModel: LikeViewModel
)
