package com.desserttime.model

data class NavigationActions(
    val onNavigateToAlarm: () -> Unit,
    val onNavigateToLogin: () -> Unit,
    val onNavigateToLikeDetail: () -> Unit,
    val onNavigateToSetting: () -> Unit,
    val onNavigateToMyInfo: () -> Unit,
    val onNavigateToReviewWrite: () -> Unit,
    val onNavigateToWheat: () -> Unit,
    val onNavigateToNoticeAndEvent: () -> Unit,
    val onNavigateToQuestion: () -> Unit,
    val onNavigationInquiryInput: () -> Unit,
    val onNavigateToMyReview: () -> Unit,
    val onNavigateToCategoryDetail: () -> Unit
)
