package com.desserttime.auth

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.desserttime.alarm.AlarmScreen
import com.desserttime.auth.inquiry.InquiryCompleteScreen
import com.desserttime.auth.inquiry.InquiryInputScreen
import com.desserttime.auth.signup.SignUpAgreeScreen
import com.desserttime.auth.signup.SignUpChooseScreen
import com.desserttime.auth.signup.SignUpCompleteScreen
import com.desserttime.auth.signup.SignUpInputScreen
import com.desserttime.category.CategoryViewModel
import com.desserttime.category.SubCategoryReviewDetailScreen
import com.desserttime.category.SubCategoryReviewScreen
import com.desserttime.controler.MainControl
import com.desserttime.core.navigation.NavGraphLabel
import com.desserttime.core.navigation.destination.AuthDestination
import com.desserttime.core.navigation.destination.MainDestination
import com.desserttime.like.LikeDetailScreen
import com.desserttime.mypage.MyPageViewModel
import com.desserttime.mypage.myinfo.MyInfoScreen
import com.desserttime.mypage.myinfo.TasteChooseScreen
import com.desserttime.mypage.notice.NoticeAndEvent
import com.desserttime.mypage.question.QuestionScreen
import com.desserttime.mypage.setting.SettingScreen
import com.desserttime.mypage.wheat.WheatScreen
import com.desserttime.mypage.withdrawal.WithdrawalCompleteScreen
import com.desserttime.mypage.withdrawal.WithdrawalScreen
import com.desserttime.review.ReviewViewModel
import com.desserttime.review.ReviewWriteScreen

fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController,
    authViewModel: AuthViewModel,
    categoryViewModel: CategoryViewModel,
    reviewViewModel: ReviewViewModel,
    myPageViewModel: MyPageViewModel
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
                },
                authViewModel = authViewModel
            )
        }

        composable(route = AuthDestination.SignUpInput.route) {
            SignUpInputScreen(
                onNavigateToSignUpChoose = {
                    navHostController.navigate(AuthDestination.SignUpChoose.route)
                },
                onBack = {
                    navHostController.popBackStack()
                },
                authViewModel = authViewModel
            )
        }

        composable(route = AuthDestination.SignUpChoose.route) {
            SignUpChooseScreen(
                onNavigateToSignUpComplete = {
                    navHostController.navigate(AuthDestination.SignUpComplete.route)
                },
                onBack = {
                    navHostController.popBackStack()
                },
                authViewModel = authViewModel
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
                },
                onTimeout = {
                    navHostController.navigate(MainDestination.Home.route) {
                        popUpTo(AuthDestination.SignUpComplete.route) {
                            inclusive = false
                        }
                    }
                }
            )
        }

        composable(route = AuthDestination.InquiryInput.route) {
            InquiryInputScreen(
                onNavigateToInquiryComplete = {
                    navHostController.navigate(AuthDestination.InquiryComplete.route)
                },
                onBack = {
                    navHostController.popBackStack()
                },
                authViewModel = authViewModel
            )
        }

        composable(route = AuthDestination.InquiryComplete.route) {
            InquiryCompleteScreen(
                onTimeout = {
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
                onNavigateToAlarm = {
                    navHostController.navigate(MainDestination.Alarm.route)
                },
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
                },
                onNavigateToReviewWrite = {
                    navHostController.navigate(MainDestination.ReviewWrite.route)
                },
                onNavigationToSubReview = {
                    navHostController.navigate(MainDestination.SubCategoryReview.route)
                },
                categoryViewModel = categoryViewModel,
                reviewViewModel = reviewViewModel,
                onNavigateToWheat = {
                    navHostController.navigate(MainDestination.Wheat.route)
                },
                onNavigateToNoticeAndEvent = {
                    navHostController.navigate(MainDestination.NoticeAndEvent.route)
                },
                myPageViewModel = myPageViewModel,
                onNavigateToQuestion = {
                    navHostController.navigate(MainDestination.Question.route)
                },
                onNavigationInquiryInput = {
                    navHostController.navigate(AuthDestination.InquiryInput.route)
                }
            )
        }

        composable(route = MainDestination.Alarm.route) {
            AlarmScreen()
        }

        composable(route = MainDestination.SubCategoryReview.route) {
            SubCategoryReviewScreen(
                categoryViewModel = categoryViewModel,
                onNavigateToCategory = {
                    navHostController.popBackStack()
                },
                onNavigateToSubCategoryReviewDetail = {
                    navHostController.navigate(MainDestination.SubCategoryReviewDetail.route)
                }
            )
        }

        composable(route = MainDestination.SubCategoryReviewDetail.route) {
            SubCategoryReviewDetailScreen(
                categoryViewModel = categoryViewModel,
                onNavigateToSubCategoryReview = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(route = MainDestination.ReviewWrite.route) {
            ReviewWriteScreen(
                reviewViewModel = reviewViewModel,
                onNavigateToReview = {
                    navHostController.popBackStack()
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
            MyInfoScreen(
                onNavigateToTasteChoose = {
                    navHostController.navigate(MainDestination.TasteChoose.route)
                },
                onBack = {
                    navHostController.popBackStack()
                },
                myPageViewModel = myPageViewModel
            )
        }

        composable(route = MainDestination.TasteChoose.route) {
            TasteChooseScreen(
                onNavigateToMyInfo = {
                    navHostController.navigate(MainDestination.MyInfo.route)
                },
                onBack = {
                    navHostController.popBackStack()
                },
                myPageViewModel = myPageViewModel
            )
        }

        composable(route = MainDestination.Wheat.route) {
            WheatScreen()
        }

        composable(route = MainDestination.NoticeAndEvent.route) {
            NoticeAndEvent(
                myPageViewModel = myPageViewModel
            )
        }

        composable(route = MainDestination.Question.route) {
            QuestionScreen()
        }
    }
}
