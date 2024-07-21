package com.desserttime.design.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.desserttime.design.extensions.toSp

/*
* Text(
       text = "Hello, Roboto!",
       style = typography.displayLarge,
  )
* */

@Immutable
data class DessertTimeTypography(
    val textStyleBold12: TextStyle,
    val textStyleBold14: TextStyle,
    val textStyleBold16: TextStyle,
    val textStyleBold18: TextStyle,
    val textStyleBold20: TextStyle,
    val textStyleBold24: TextStyle,
    val textStyleBold26: TextStyle,
    val textStyleBold30: TextStyle,
    val textStyleRegular12: TextStyle,
    val textStyleRegular14: TextStyle,
    val textStyleRegular16: TextStyle,
    val textStyleRegular18: TextStyle,
    val textStyleRegular20: TextStyle,
    val textStyleRegular24: TextStyle,
    val textStyleRegular30: TextStyle,
    val textStyleLight12: TextStyle,
    val textStyleLight14: TextStyle,
    val textStyleLight16: TextStyle,
    val textStyleLight18: TextStyle,
    val textStyleLight20: TextStyle,
    val textStyleLight24: TextStyle,
    val textStyleLight30: TextStyle,
    val textStyleItalic12: TextStyle,
    val textStyleItalic14: TextStyle,
    val textStyleItalic16: TextStyle,
    val textStyleItalic18: TextStyle,
    val textStyleItalic20: TextStyle,
    val textStyleItalic24: TextStyle,
    val textStyleItalic30: TextStyle
)

val LocalDessertTimeTypography = staticCompositionLocalOf {
    DessertTimeTypography(
        textStyleBold12 = TextStyle.Default,
        textStyleBold14 = TextStyle.Default,
        textStyleBold16 = TextStyle.Default,
        textStyleBold18 = TextStyle.Default,
        textStyleBold20 = TextStyle.Default,
        textStyleBold24 = TextStyle.Default,
        textStyleBold26 = TextStyle.Default,
        textStyleBold30 = TextStyle.Default,
        textStyleRegular12 = TextStyle.Default,
        textStyleRegular14 = TextStyle.Default,
        textStyleRegular16 = TextStyle.Default,
        textStyleRegular18 = TextStyle.Default,
        textStyleRegular20 = TextStyle.Default,
        textStyleRegular24 = TextStyle.Default,
        textStyleRegular30 = TextStyle.Default,
        textStyleLight12 = TextStyle.Default,
        textStyleLight14 = TextStyle.Default,
        textStyleLight16 = TextStyle.Default,
        textStyleLight18 = TextStyle.Default,
        textStyleLight20 = TextStyle.Default,
        textStyleLight24 = TextStyle.Default,
        textStyleLight30 = TextStyle.Default,
        textStyleItalic12 = TextStyle.Default,
        textStyleItalic14 = TextStyle.Default,
        textStyleItalic16 = TextStyle.Default,
        textStyleItalic18 = TextStyle.Default,
        textStyleItalic20 = TextStyle.Default,
        textStyleItalic24 = TextStyle.Default,
        textStyleItalic30 = TextStyle.Default
    )
}
