package com.desserttime.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.desserttime.auth.inquiry.InquiryCompleteScreen
import com.desserttime.auth.inquiry.InquiryInputScreen
import com.desserttime.auth.signup.SignUpAgreeScreen
import com.desserttime.auth.signup.SignUpChooseScreen
import com.desserttime.auth.signup.SignUpInputScreen
import com.desserttime.core.navigation.NavGraphLabel
import com.desserttime.core.navigation.destination.AuthDestination

fun NavGraphBuilder.inquiryNavGraph(
    navHostController: NavHostController,
) {
    navigation(
        startDestination = AuthDestination.Login.route,
        route = NavGraphLabel.AUTH
    ) {
        composable(route = AuthDestination.InquiryInput.route) {
            InquiryInputScreen(
                onNavigateToInquiryInput= {
                    navHostController.navigate(AuthDestination.InquiryComplete.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = AuthDestination.InquiryComplete.route) {
            InquiryCompleteScreen(
                onNavigateToLogin = {
                    navHostController.navigate(AuthDestination.Login.route) {
                        popUpTo(AuthDestination.InquiryComplete.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }
    }
}