package com.desserttime.core.navigation.destination

import com.desserttime.core.navigation.NavRouteLabel

sealed class RootDestination : Destination {
    object Splash : RootDestination() {
        override val route = NavRouteLabel.SPLASH
    }
}