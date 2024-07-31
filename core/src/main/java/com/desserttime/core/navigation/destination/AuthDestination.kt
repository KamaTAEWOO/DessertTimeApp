package com.desserttime.core.navigation.destination

import com.desserttime.core.navigation.NavRouteLabel

sealed class AuthDestination : Destination {
    object Login : AuthDestination() {
        override val route = NavRouteLabel.LOGIN
    }

    object SignUpAgree : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP_AGREE
    }

    object SignUpInput : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP_INPUT
    }

    object SignUpChoose : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP_CHOOSE
    }

    object SignUpComplete : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP_COMPLETE
    }

    object InquiryInput : AuthDestination() {
        override val route = NavRouteLabel.INQUIRY_INPUT
    }

    object InquiryComplete : AuthDestination() {
        override val route = NavRouteLabel.INQUIRY_COMPLETE
    }
}
