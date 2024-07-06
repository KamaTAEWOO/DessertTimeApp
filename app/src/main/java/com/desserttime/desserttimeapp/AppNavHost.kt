package com.desserttime.desserttimeapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.desserttime.auth.authNavGraph
import com.desserttime.auth.inquiryNavGraph
import com.desserttime.auth.login.LoginScreen
import com.desserttime.core.navigation.NavGraphLabel
import com.desserttime.core.navigation.destination.AuthDestination
import com.desserttime.core.navigation.destination.RootDestination
import com.desserttime.desserttimeapp.splash.SplashScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navHostController,
        startDestination = RootDestination.Splash.route,
        modifier = Modifier.fillMaxSize(),
        route = NavGraphLabel.ROOT
    ) {
        composable(route = RootDestination.Splash.route) {
            SplashScreen {
                navHostController.navigate(AuthDestination.Login.route) {
                    popUpTo(RootDestination.Splash.route) {
                        inclusive = true
                    }
                }
            }
        }

        composable(route = AuthDestination.Login.route) {
            LoginScreen(
                onNavigateToSignUpAgree = {
                    navHostController.navigate(AuthDestination.SignUpAgree.route)
                },
                onNavigationToInquiryInput = {
                    navHostController.navigate(AuthDestination.InquiryInput.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        authNavGraph(
            navHostController = navHostController
        )

        inquiryNavGraph(
            navHostController = navHostController
        )
    }
}
