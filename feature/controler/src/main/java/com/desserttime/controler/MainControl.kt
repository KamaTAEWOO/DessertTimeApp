package com.desserttime.controler

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.desserttime.category.CategoryScreen
import com.desserttime.core.navigation.destination.MainDestination
import com.desserttime.design.R
import com.desserttime.design.theme.Manatee
import com.desserttime.home.HomeScreen
import com.desserttime.like.LikeScreen
import com.desserttime.mypage.MyPageScreen
import com.desserttime.review.ReviewScreen

@Composable
fun MainControl() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MainDestination.Home.route,
            Modifier.padding(innerPadding)
        ) {
            composable(MainDestination.Home.route) { HomeScreen() }
            composable(MainDestination.Category.route) { CategoryScreen() }
            composable(MainDestination.Like.route) { LikeScreen() }
            composable(MainDestination.MyPage.route) { MyPageScreen() }
            composable(MainDestination.Review.route) { ReviewScreen() }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.White,
        contentColor = Manatee,
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bottom_home),
                    contentDescription = stringResource(id = R.string.txt_bottom_home)
                )
            },
            label = { Text(stringResource(id = R.string.txt_bottom_home), color = Manatee) },
            selected = currentRoute == MainDestination.Home.route,
            onClick = {
                if (currentRoute != MainDestination.Home.route) {
                    navController.navigate(MainDestination.Home.route) {
                        // Avoid multiple instances of the same destination
                        popUpTo(MainDestination.Home.route) { inclusive = true }
                    }
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bottom_category),
                    contentDescription = stringResource(id = R.string.txt_bottom_category)
                )
            },
            label = { Text(stringResource(id = R.string.txt_bottom_category), color = Manatee) },
            selected = currentRoute == MainDestination.Category.route,
            onClick = {
                if (currentRoute != MainDestination.Category.route) {
                    navController.navigate(MainDestination.Category.route) {
                        popUpTo(MainDestination.Category.route) { inclusive = true }
                    }
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bottom_review),
                    contentDescription = stringResource(id = R.string.txt_bottom_review)
                )
            },
            label = { Text(stringResource(id = R.string.txt_bottom_review), color = Manatee) },
            selected = currentRoute == MainDestination.Review.route,
            onClick = {
                if (currentRoute != MainDestination.Review.route) {
                    navController.navigate(MainDestination.Review.route) {
                        popUpTo(MainDestination.Review.route) { inclusive = true }
                    }
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bottom_like),
                    contentDescription = stringResource(id = R.string.txt_bottom_like)
                )
            },
            label = { Text(stringResource(id = R.string.txt_bottom_like), color = Manatee) },
            selected = currentRoute == MainDestination.Like.route,
            onClick = {
                if (currentRoute != MainDestination.Like.route) {
                    navController.navigate(MainDestination.Like.route) {
                        popUpTo(MainDestination.Like.route) { inclusive = true }
                    }
                }
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bottom_mypage),
                    contentDescription = stringResource(id = R.string.txt_bottom_my_page)
                )
            },
            label = { Text(stringResource(id = R.string.txt_bottom_my_page), color = Manatee) },
            selected = currentRoute == MainDestination.MyPage.route,
            onClick = {
                if (currentRoute != MainDestination.MyPage.route) {
                    navController.navigate(MainDestination.MyPage.route) {
                        popUpTo(MainDestination.MyPage.route) { inclusive = true }
                    }
                }
            }
        )
    }
}
