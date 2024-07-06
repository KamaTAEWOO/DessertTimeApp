package com.desserttime.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.desserttime.auth.login.LoginScreen
import com.desserttime.auth.signup.SignUpAgreeScreen
import com.desserttime.auth.signup.SignUpChooseScreen
import com.desserttime.auth.signup.SignUpCompleteScreen
import com.desserttime.auth.signup.SignUpInputScreen
import com.desserttime.core.navigation.NavGraphLabel
import com.desserttime.core.navigation.destination.AuthDestination

fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController,
) {
    navigation(
        startDestination = AuthDestination.Login.route,
        route = NavGraphLabel.AUTH
    ) {
        composable(route = AuthDestination.Login.route) {
            LoginScreen(
                onNavigateToSignUpAgree = {
                    navHostController.navigate(AuthDestination.SignUpAgree.route)
                },
                onBack = {
                    navHostController.navigate(AuthDestination.SignUpAgree.route) {
                        popUpTo(AuthDestination.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = AuthDestination.SignUpAgree.route) {
            SignUpAgreeScreen(
                onNavigateToSignUpInput = {
                    navHostController.navigate(AuthDestination.SignUpInput.route) {
                        popUpTo(AuthDestination.SignUpAgree.route) {
                            inclusive = true
                        }
                    }
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = AuthDestination.SignUpInput.route) {
            SignUpInputScreen(
                onNavigateToSignUpChoose = {
                    navHostController.navigate(AuthDestination.SignUpChoose.route) {
                        popUpTo(AuthDestination.SignUpInput.route) {
                            inclusive = true
                        }
                    }
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = AuthDestination.SignUpChoose.route) {
            SignUpChooseScreen(
                onNavigateToSignUpComplete = {
                    navHostController.navigate(AuthDestination.SignUpComplete.route) {
                        popUpTo(AuthDestination.SignUpChoose.route) {
                            inclusive = true
                        }
                    }
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = AuthDestination.SignUpComplete.route) {
//            SignUpCompleteScreen(
////                onNavigateToLogin = {
////                    navHostController.navigate(Lo.Home.route) {
////                        popUpTo(AuthDestination.SignUpComplete.route) {
////                            inclusive = true
////                        }
////                    }
////                },
////                onBack = {
////                    navHostController.popBackStack()
////                }
//            )
        }
    }
}
