package com.desserttime.core.navigation.destination

import com.desserttime.core.navigation.NavRouteLabel

sealed class MainDestination : Destination {
    object Main : MainDestination() {
        override val route = NavRouteLabel.MAIN
    }

    object Alarm : MainDestination() {
        override val route = NavRouteLabel.ALARM
    }

    object Home : MainDestination() {
        override val route = NavRouteLabel.HOME
    }

    object Category : MainDestination() {
        override val route = NavRouteLabel.CATEGORY
    }

    object SubCategoryReview : MainDestination() {
        override val route = NavRouteLabel.SUB_CATEGORY_REVIEW
    }

    object SubCategoryReviewDetail : MainDestination() {
        override val route = NavRouteLabel.SUB_CATEGORY_REVIEW_DETAIL
    }

    object MyPage : MainDestination() {
        override val route = NavRouteLabel.MY_PAGE
    }

    object Like : MainDestination() {
        override val route = NavRouteLabel.LIKE
    }

    object Review : MainDestination() {
        override val route = NavRouteLabel.REVIEW
    }

    object ReviewWrite : MainDestination() {
        override val route = NavRouteLabel.REVIEW_WRITE
    }

    object LikeDetail : MainDestination() {
        override val route = NavRouteLabel.LIKE_DETAIL
    }

    object Setting : MainDestination() {
        override val route = NavRouteLabel.SETTING
    }

    object Withdrawal : MainDestination() {
        override val route = NavRouteLabel.WITHDRAWAL
    }

    object WithdrawalComplete : MainDestination() {
        override val route = NavRouteLabel.WITHDRAWAL_COMPLETE
    }

    object MyInfo : MainDestination() {
        override val route = NavRouteLabel.MY_INFO
    }

    object TasteChoose : MainDestination() {
        override val route = NavRouteLabel.TASTE_CHOOSE
    }
}
