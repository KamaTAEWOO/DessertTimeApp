package com.desserttime.auth.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

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
            Text(
                text = "Login Screen",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black, // 텍스트 색상을 검정색으로 설정
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}