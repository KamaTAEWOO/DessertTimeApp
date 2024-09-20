package com.desserttime.desserttimeapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.desserttime.auth.AuthViewModel
import com.desserttime.auth.authNavGraph
import com.desserttime.auth.login.LoginScreen
import com.desserttime.category.CategoryViewModel
import com.desserttime.core.navigation.NavGraphLabel
import com.desserttime.core.navigation.destination.AuthDestination
import com.desserttime.core.navigation.destination.MainDestination
import com.desserttime.core.navigation.destination.RootDestination
import com.desserttime.desserttimeapp.splash.SplashScreen
import com.desserttime.review.ReviewViewModel

@Composable
fun AppNavHost(
    navHostController: NavHostController = rememberNavController()
) {
    val authViewModel: AuthViewModel = hiltViewModel()
    val categoryViewModel: CategoryViewModel = hiltViewModel()
    val reviewViewModel: ReviewViewModel = hiltViewModel()

    NavHost(
        navController = navHostController,
        startDestination = RootDestination.Splash.route,
        modifier = Modifier.fillMaxSize(),
        route = NavGraphLabel.ROOT
    ) {
        // navHostController.navigate(AuthDestination.Login.route) {
        composable(route = RootDestination.Splash.route) {
            SplashScreen {
                navHostController.navigate(MainDestination.Home.route) {
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
                onNavigateToInquiryInput = {
                    navHostController.navigate(AuthDestination.InquiryInput.route)
                },
                onNavigateToHome = {
                    navHostController.navigate(MainDestination.Home.route)
                },
                onBack = {
                    navHostController.popBackStack()
                },
                authViewModel = authViewModel
            )
        }

        authNavGraph(
            navHostController = navHostController,
            authViewModel = authViewModel,
            categoryViewModel = categoryViewModel,
            reviewViewModel = reviewViewModel
        )
    }
}
