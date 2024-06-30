package com.desserttime.desserttimeapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.desserttime.core.navigation.destination.RootDestination
import com.desserttime.desserttimeapp.splash.NextScreen
import com.desserttime.desserttimeapp.splash.SplashScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = RootDestination.Splash.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = RootDestination.Splash.route) {
            SplashScreen {
                navHostController.navigate(RootDestination.Next.route) {
                    popUpTo(RootDestination.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }

        composable(route = RootDestination.Next.route) {
            NextScreen()
        }
    }
}
