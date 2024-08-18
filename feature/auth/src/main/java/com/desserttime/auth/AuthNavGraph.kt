package com.desserttime.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.desserttime.auth.inquiry.InquiryCompleteScreen
import com.desserttime.auth.inquiry.InquiryInputScreen
import com.desserttime.auth.signup.SignUpAgreeScreen
import com.desserttime.auth.signup.SignUpChooseScreen
import com.desserttime.auth.signup.SignUpCompleteScreen
import com.desserttime.auth.signup.SignUpInputScreen
import com.desserttime.controler.MainControl
import com.desserttime.core.navigation.NavGraphLabel
import com.desserttime.core.navigation.destination.AuthDestination
import com.desserttime.core.navigation.destination.MainDestination
import com.desserttime.like.LikeDetailScreen
import com.desserttime.mypage.myinfo.MyInfoScreen
import com.desserttime.mypage.setting.SettingScreen
import com.desserttime.mypage.withdrawal.WithdrawalCompleteScreen
import com.desserttime.mypage.withdrawal.WithdrawalScreen

fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController
) {
    navigation(
        startDestination = AuthDestination.Login.route,
        route = NavGraphLabel.AUTH
    ) {
        composable(route = AuthDestination.SignUpAgree.route) {
            SignUpAgreeScreen(
                onNavigateToSignUpInput = {
                    navHostController.navigate(AuthDestination.SignUpInput.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = AuthDestination.SignUpInput.route) {
            SignUpInputScreen(
                onNavigateToSignUpChoose = {
                    navHostController.navigate(AuthDestination.SignUpChoose.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = AuthDestination.SignUpChoose.route) {
            SignUpChooseScreen(
                onNavigateToSignUpComplete = {
                    navHostController.navigate(AuthDestination.SignUpComplete.route)
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = AuthDestination.SignUpComplete.route) {
            SignUpCompleteScreen(
                onNavigateToSignIn = {
                    navHostController.navigate(AuthDestination.Login.route) {
                        popUpTo(AuthDestination.SignUpComplete.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = AuthDestination.InquiryInput.route) {
            InquiryInputScreen(
                onNavigateToInquiryInput = {
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
                }
            )
        }

        composable(route = MainDestination.Home.route) {
            MainControl(
                onNavigateToLogin = {
                    navHostController.navigate(AuthDestination.Login.route) {
                        popUpTo(AuthDestination.InquiryComplete.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToLikeDetail = {
                    navHostController.navigate(MainDestination.LikeDetail.route)
                },
                onNavigateToSetting = {
                    navHostController.navigate(MainDestination.Setting.route)
                },
                onNavigateToMyInfo = {
                    navHostController.navigate(MainDestination.MyInfo.route)
                }
            )
        }

        composable(route = MainDestination.LikeDetail.route) {
            LikeDetailScreen(
                onNavigateToLike = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = MainDestination.Setting.route) {
            SettingScreen(
                onNavigateToHome = {
                    navHostController.navigate(MainDestination.Home.route) {
                        popUpTo(MainDestination.Setting.route) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToWithdrawal = {
                    navHostController.navigate(MainDestination.Withdrawal.route) {
                        popUpTo(MainDestination.Setting.route) {
                            inclusive = true
                        }
                    }
                },
                onBack = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = MainDestination.Withdrawal.route) {
            WithdrawalScreen(
                onNavigateToWithdrawalComplete = {
                    navHostController.navigate(MainDestination.WithdrawalComplete.route) {
                        popUpTo(MainDestination.Withdrawal.route) {
                            inclusive = true
                        }
                    }
                },
                onBack = {
                    navHostController.navigate(MainDestination.Setting.route) {
                        popUpTo(MainDestination.Withdrawal.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = MainDestination.WithdrawalComplete.route) {
            WithdrawalCompleteScreen(
                onNavigateToHome = {
                    navHostController.navigate(MainDestination.Home.route) {
                        popUpTo(MainDestination.WithdrawalComplete.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = MainDestination.MyInfo.route) {
            MyInfoScreen()
        }
    }
}
