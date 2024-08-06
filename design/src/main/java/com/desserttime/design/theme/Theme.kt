package com.desserttime.design.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.desserttime.design.extensions.toSp

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
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    // Define Typography with Roboto FontFamily
    val typography = DessertTimeTypography(
        textStyleBold12 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 12.dp.toSp()
        ),
        textStyleBold14 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 14.dp.toSp()
        ),
        textStyleBold16 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 16.dp.toSp()
        ),
        textStyleBold18 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 18.dp.toSp()
        ),
        textStyleBold20 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 20.dp.toSp()
        ),
        textStyleBold24 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 24.dp.toSp()
        ),
        textStyleBold26 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 26.dp.toSp()
        ),
        textStyleBold30 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Bold,
            fontSize = 30.dp.toSp()
        ),
        textStyleRegular12 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 12.dp.toSp()
        ),
        textStyleRegular14 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 14.dp.toSp()
        ),
        textStyleRegular16 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 16.dp.toSp()
        ),
        textStyleRegular18 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 18.dp.toSp()
        ),
        textStyleRegular20 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 20.dp.toSp()
        ),
        textStyleRegular24 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 24.dp.toSp()
        ),
        textStyleRegular26 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 26.dp.toSp()
        ),
        textStyleRegular30 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 30.dp.toSp()
        ),
        textStyleLight12 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Light,
            fontSize = 12.dp.toSp()
        ),
        textStyleLight14 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Light,
            fontSize = 14.dp.toSp()
        ),
        textStyleLight16 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Light,
            fontSize = 16.dp.toSp()
        ),
        textStyleLight18 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Light,
            fontSize = 18.dp.toSp()
        ),
        textStyleLight20 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Light,
            fontSize = 20.dp.toSp()
        ),
        textStyleLight24 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Light,
            fontSize = 24.dp.toSp()
        ),
        textStyleLight30 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Light,
            fontSize = 30.dp.toSp()
        ),
        textStyleItalic12 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 12.dp.toSp()
        ),
        textStyleItalic14 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 14.dp.toSp()
        ),
        textStyleItalic16 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 16.dp.toSp()
        ),
        textStyleItalic18 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 18.dp.toSp()
        ),
        textStyleItalic20 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 20.dp.toSp()
        ),
        textStyleItalic24 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 24.dp.toSp()
        ),
        textStyleItalic30 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 30.dp.toSp()
        ),
        textStyleMedium12 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 12.dp.toSp()
        ),
        textStyleMedium14 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 14.dp.toSp()
        ),
        textStyleMedium16 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 16.dp.toSp()
        ),
        textStyleMedium18 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 18.dp.toSp()
        ),
        textStyleMedium20 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 20.dp.toSp()
        ),
        textStyleMedium22 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 22.dp.toSp()
        ),
        textStyleMedium24 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 24.dp.toSp()
        ),
        textStyleMedium26 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 26.dp.toSp()
        ),
        textStyleMedium28 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 28.dp.toSp()
        ),
        textStyleMedium30 = TextStyle(
            fontFamily = roboto,
            fontWeight = FontWeight.Medium,
            fontSize = 30.dp.toSp()
        )
    )

    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = MainColor.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        // typography = typography1,
        content = content
    )

    CompositionLocalProvider(
        LocalDessertTimeTypography provides typography
    ) {
        MaterialTheme(content = content)
    }
}

object DessertTimeTheme {
    val typography: DessertTimeTypography
        @Composable
        get() = LocalDessertTimeTypography.current
}
