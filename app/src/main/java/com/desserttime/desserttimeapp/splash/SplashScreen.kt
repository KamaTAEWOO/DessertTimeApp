package com.desserttime.desserttimeapp.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.desserttime.design.theme.MainColor
import com.desserttime.desserttimeapp.R

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(3000)
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash_icon),
            contentDescription = R.string.app_name.toString(),
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize()
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen {}
}
