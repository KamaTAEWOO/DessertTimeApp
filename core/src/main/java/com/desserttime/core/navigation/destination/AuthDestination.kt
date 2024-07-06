package com.desserttime.core.navigation.destination

import com.desserttime.core.navigation.NavRouteLabel

sealed class AuthDestination : Destination {
    object Login : AuthDestination() {
        override val route = NavRouteLabel.LOGIN
    }

    object SignUp02 : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP02
    }

    object SignUp03 : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP03
    }

    object SignUp04 : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP04
    }

    object SignUp05 : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP05
    }

    object SignUp06 : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP06
    }

    object SignUp07 : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP07
    }

    object SignUp08 : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP08
    }

    object Inquiry01 : AuthDestination() {
        override val route = NavRouteLabel.INQUIRY01
    }

    object Inquiry02 : AuthDestination() {
        override val route = NavRouteLabel.INQUIRY02
    }

    object Inquiry03 : AuthDestination() {
        override val route = NavRouteLabel.INQUIRY03
    }

    object Inquiry04 : AuthDestination() {
        override val route = NavRouteLabel.INQUIRY04
    }
}