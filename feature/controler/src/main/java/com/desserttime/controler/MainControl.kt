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
import com.desserttime.core.navigation.destination.MainDestination
import com.desserttime.design.R
import com.desserttime.design.theme.MainColor
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
    Spacer(Modifier.padding(bottom = 42.dp))
}

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selectedColor = MainColor
    val unselectedColor = Manatee

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color.Transparent
    ) {
        NavigationBar(
            containerColor = Color.Transparent, // Set transparent to avoid background color interference
            contentColor = Manatee,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
                .background(Color.White)
                .shadow(2.dp)
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bottom_home),
                        contentDescription = stringResource(id = R.string.txt_bottom_home),
                        tint = if (currentRoute == MainDestination.Home.route) selectedColor else unselectedColor
                    )
                },
                label = {
                    Text(
                        stringResource(id = R.string.txt_bottom_home),
                        color = if (currentRoute == MainDestination.Home.route) selectedColor else unselectedColor
                    )
                },
                selected = currentRoute == MainDestination.Home.route,
                onClick = {
                    if (currentRoute != MainDestination.Home.route) {
                        navController.navigate(MainDestination.Home.route) {
                            // Avoid multiple instances of the same destination
                            popUpTo(MainDestination.Home.route) { inclusive = true }
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White, // Ensuring no border color
                    selectedIconColor = selectedColor,
                    unselectedIconColor = unselectedColor,
                    selectedTextColor = selectedColor,
                    unselectedTextColor = unselectedColor
                )
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bottom_category),
                        contentDescription = stringResource(id = R.string.txt_bottom_category),
                        tint = if (currentRoute == MainDestination.Category.route) selectedColor else unselectedColor
                    )
                },
                label = {
                    Text(
                        stringResource(id = R.string.txt_bottom_category),
                        color = if (currentRoute == MainDestination.Category.route) selectedColor else unselectedColor
                    )
                },
                selected = currentRoute == MainDestination.Category.route,
                onClick = {
                    if (currentRoute != MainDestination.Category.route) {
                        navController.navigate(MainDestination.Category.route) {
                            popUpTo(MainDestination.Category.route) { inclusive = true }
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
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bottom_review),
                        contentDescription = stringResource(id = R.string.txt_bottom_review),
                        tint = if (currentRoute == MainDestination.Review.route) selectedColor else unselectedColor
                    )
                },
                label = {
                    Text(
                        stringResource(id = R.string.txt_bottom_review),
                        color = if (currentRoute == MainDestination.Review.route) selectedColor else unselectedColor
                    )
                },
                selected = currentRoute == MainDestination.Review.route,
                onClick = {
                    if (currentRoute != MainDestination.Review.route) {
                        navController.navigate(MainDestination.Review.route) {
                            popUpTo(MainDestination.Review.route) { inclusive = true }
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
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bottom_like),
                        contentDescription = stringResource(id = R.string.txt_bottom_like),
                        tint = if (currentRoute == MainDestination.Like.route) selectedColor else unselectedColor
                    )
                },
                label = {
                    Text(
                        stringResource(id = R.string.txt_bottom_like),
                        color = if (currentRoute == MainDestination.Like.route) selectedColor else unselectedColor
                    )
                },
                selected = currentRoute == MainDestination.Like.route,
                onClick = {
                    if (currentRoute != MainDestination.Like.route) {
                        navController.navigate(MainDestination.Like.route) {
                            popUpTo(MainDestination.Like.route) { inclusive = true }
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
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bottom_mypage),
                        contentDescription = stringResource(id = R.string.txt_bottom_my_page),
                        tint = if (currentRoute == MainDestination.MyPage.route) selectedColor else unselectedColor
                    )
                },
                label = {
                    Text(
                        stringResource(id = R.string.txt_bottom_my_page),
                        color = if (currentRoute == MainDestination.MyPage.route) selectedColor else unselectedColor
                    )
                },
                selected = currentRoute == MainDestination.MyPage.route,
                onClick = {
                    if (currentRoute != MainDestination.MyPage.route) {
                        navController.navigate(MainDestination.MyPage.route) {
                            popUpTo(MainDestination.MyPage.route) { inclusive = true }
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
