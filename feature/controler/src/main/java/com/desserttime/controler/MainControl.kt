package com.desserttime.controler

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.desserttime.category.CategoryScreen
import com.desserttime.category.SubCategoryReviewScreen
import com.desserttime.core.navigation.destination.MainDestination
import com.desserttime.design.R
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.Manatee
import com.desserttime.home.HomeScreen
import com.desserttime.like.LikeScreen
import com.desserttime.model.NavigationActions
import com.desserttime.model.ViewModels
import com.desserttime.mypage.MyPageScreen
import com.desserttime.review.ReviewScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainControl(
    navigationActions: NavigationActions,
    viewModels: ViewModels
) {
    SetStatusBarColor(color = Color.White)
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainDestination.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(MainDestination.Home.route) {
                HomeScreen(
                    navigationActions.onNavigateToLogin,
                    navigationActions.onNavigateToAlarm
                )
            }
            composable(MainDestination.Category.route) {
                CategoryScreen(
                    viewModels.categoryViewModel
                ) { navController.navigate(MainDestination.SubCategoryReview.route) }
            }
            composable(MainDestination.Like.route) {
                LikeScreen(
                    navigationActions.onNavigateToLikeDetail,
                    viewModels.likeViewModel
                )
            }
            composable(MainDestination.MyPage.route) {
                MyPageScreen(
                    navigationActions.onNavigateToLogin,
                    navigationActions.onNavigateToSetting,
                    navigationActions.onNavigateToMyInfo,
                    navigationActions.onNavigateToWheat,
                    navigationActions.onNavigateToNoticeAndEvent,
                    viewModels.myPageViewModel,
                    navigationActions.onNavigateToQuestion,
                    navigationActions.onNavigationInquiryInput,
                    navigationActions.onNavigateToMyReview
                )
            }
            composable(MainDestination.Review.route) {
                ReviewScreen(
                    viewModels.reviewViewModel,
                    navigationActions.onNavigateToReviewWrite
                )
            }
            composable(route = MainDestination.SubCategoryReview.route) {
                SubCategoryReviewScreen(
                    categoryViewModel = viewModels.categoryViewModel,
                    onNavigateToCategory = {
                        navController.popBackStack()
                    },
                    onNavigateToSubCategoryReviewDetail = {
                        navigationActions.onNavigateToCategoryDetail()
                    }
                )
            }
        }
    }
    Spacer(Modifier.padding(bottom = 42.dp))
}

data class BottomNavItem(
    val route: String,
    val iconResId: Int,
    val labelResId: Int
)

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selectedColor = MainColor
    val unselectedColor = Manatee

    val items = listOf(
        BottomNavItem(
            route = MainDestination.Home.route,
            iconResId = R.drawable.ic_bottom_home,
            labelResId = R.string.txt_bottom_home
        ),
        BottomNavItem(
            route = MainDestination.Category.route,
            iconResId = R.drawable.ic_bottom_category,
            labelResId = R.string.txt_bottom_category
        ),
        BottomNavItem(
            route = MainDestination.Review.route,
            iconResId = R.drawable.ic_bottom_review,
            labelResId = R.string.txt_bottom_review
        ),
        BottomNavItem(
            route = MainDestination.Like.route,
            iconResId = R.drawable.ic_bottom_like,
            labelResId = R.string.txt_bottom_like
        ),
        BottomNavItem(
            route = MainDestination.MyPage.route,
            iconResId = R.drawable.ic_bottom_mypage,
            labelResId = R.string.txt_bottom_my_page
        )
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.Transparent
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            contentColor = Manatee,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                .background(Color.White)
                .shadow(2.dp)
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.route
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconResId),
                            contentDescription = stringResource(id = item.labelResId),
                            tint = if (isSelected) selectedColor else unselectedColor
                        )
                    },
                    label = {
                        Text(
                            stringResource(id = item.labelResId),
                            color = if (isSelected) selectedColor else unselectedColor
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        if (!isSelected) {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.White,
                        selectedIconColor = selectedColor,
                        unselectedIconColor = unselectedColor,
                        selectedTextColor = selectedColor,
                        unselectedTextColor = unselectedColor
                    )
                )
            }
        }
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = color)
    }
}
