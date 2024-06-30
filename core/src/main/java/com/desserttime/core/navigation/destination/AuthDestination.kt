package com.desserttime.core.navigation.destination

import com.cmc.curtaincall.common.navigation.NavRouteLabel

sealed class AuthDestination : Destination {
    object Login : AuthDestination() {
        override val route = NavRouteLabel.LOGIN
    }

    object SignUpTerms : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP_TERMS
    }

    object SignUpInput : AuthDestination() {
        override val route = NavRouteLabel.SIGNUP_INPUT
    }
}