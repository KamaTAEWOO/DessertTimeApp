package com.desserttime.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.desserttime.category.CategoryScreen
import com.desserttime.core.navigation.destination.MainDestination
import com.desserttime.like.LikeScreen
import com.desserttime.mypage.MyPageScreen
import com.desserttime.review.WritingReviewScreen

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        //bottomBar = { BottomNavBar() }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            Modifier.padding(innerPadding)
        ) {
            composable(MainDestination.Home.route) { HomeScreen() }
            composable(MainDestination.Category.route) { CategoryScreen() }
            composable(MainDestination.Like.route) { LikeScreen() }
            composable(MainDestination.MyPage.route) { MyPageScreen() }
            composable(MainDestination.WritingReview.route) { WritingReviewScreen() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BottomNavBar() {
//    val navController = rememberNavController()
//    BottomNavigation(
//        containerColor = Color.White,
//        contentColor = Color.Black,
//        elevation = 8.dp
//    ) {
//        BottomNavigationItem(
//            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
//            label = { Text("Home") },
//            selected = false, // Update based on navigation state
//            onClick = { navController.navigate("home") }
//        )
//        BottomNavigationItem(
//            icon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
//            label = { Text("Search") },
//            selected = false, // Update based on navigation state
//            onClick = { navController.navigate("search") }
//        )
//        BottomNavigationItem(
//            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
//            label = { Text("Profile") },
//            selected = false, // Update based on navigation state
//            onClick = { navController.navigate("profile") }
//        )
//    }
//}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}