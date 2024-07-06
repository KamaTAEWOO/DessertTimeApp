package com.desserttime.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.auth.login.LoginScreen
import com.desserttime.core.navigation.destination.AuthDestination
import com.desserttime.design.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SignUpAgreeScreen(
    onNavigateToSignUpInput: () -> Unit = {},
    onBack: () -> Unit
) {
    // SystemUiController를 사용하여 상태 바 색상 설정
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Spacer(Modifier.padding(top = 32.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_cake),
            contentDescription = "img_login_logo",
            modifier = Modifier
                .size(88.dp, 56.dp)
                .padding(start= 32.dp),
            contentScale = ContentScale.FillBounds
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //SignUpAgreeScreen()
}