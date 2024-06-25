package com.desserttime.design.theme

import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

@Immutable
data class DessertTimeShapes(
    val bottomButton: Shape
)

val LocalCurtainCallShapes = staticCompositionLocalOf {
    DessertTimeShapes(
        bottomButton = ShapeDefaults.Small
    )
}
