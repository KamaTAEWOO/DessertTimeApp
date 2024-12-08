package com.desserttime.desserttimeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.desserttime.design.theme.DessertTimeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        // 시스템 창이 콘텐츠를 가리지 않도록 설정합니다.
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            DessertTimeAppTheme {
                AppNavHost()
            }
        }
    }
}
