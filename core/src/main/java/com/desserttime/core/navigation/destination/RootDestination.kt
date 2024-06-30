package com.desserttime.core.navigation.destination

import com.cmc.curtaincall.common.navigation.NavRouteLabel

sealed class RootDestination : Destination {
    object Splash : RootDestination() {
        override val route = NavRouteLabel.SPLASH
    }

    object Next : RootDestination() {
        override val route = NavRouteLabel.NEXT
    }
}