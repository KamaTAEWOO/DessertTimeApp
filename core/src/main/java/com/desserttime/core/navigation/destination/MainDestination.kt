package com.desserttime.core.navigation.destination

import com.desserttime.core.navigation.NavRouteLabel

sealed class MainDestination : Destination {
    object Home : MainDestination() {
        override val route = NavRouteLabel.HOME
    }

    object Category : MainDestination() {
        override val route = NavRouteLabel.CATEGORY
    }

    object MyPage : MainDestination() {
        override val route = NavRouteLabel.MY_PAGE
    }

    object Like : MainDestination() {
        override val route = NavRouteLabel.LIKE
    }

    object WritingReview : MainDestination() {
        override val route = NavRouteLabel.WRITING_REVOEW
    }
}