package com.desserttime.design.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = MainColor, // 주색상
    secondary = MainColor, // 보조 색상, 주 색상과 함께 사용하여 UI의 강조 및 보조 요소에 사용됨
    tertiary = MainColor // 삼차 색상, 주 색상과 보조 색상 외에 추가적인 색상으로 배경이나 구분선 등 다양한 요소에 사용
)

private val LightColorScheme = lightColorScheme(
    primary = MainColor,
    secondary = MainColor,
    tertiary = MainColor
)

@Composable
fun DessertTimeAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

object DessertTimeTheme {
    val typography: DessertTimeTypography
        @Composable
        get() = LocalDessertTimeTypography.current
}
