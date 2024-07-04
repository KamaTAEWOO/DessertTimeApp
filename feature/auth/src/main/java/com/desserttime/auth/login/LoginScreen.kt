package com.desserttime.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.desserttime.design.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.desserttime.design.theme.*

@Composable
fun LoginScreen() {
    // SystemUiController를 사용하여 상태 바 색상 설정
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White // 전체 화면 배경을 흰색으로 설정
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_login_logo),
                contentDescription = R.string.img_login_logo.toString(),
                modifier = Modifier
                    .align(Alignment.Center)
                    .wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginScreen()
}