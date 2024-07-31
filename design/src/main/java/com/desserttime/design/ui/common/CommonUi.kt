package com.desserttime.design.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.desserttime.design.theme.DessertTimeTheme

object CommonUi {

    // *********************************************************************************************
    // *************************************Public Method*******************************************
    // *********************************************************************************************

    // 회색 라인
    @Composable
    fun GrayLine(modifier: Modifier) {
        Box(
            modifier = modifier
        )
    }

    // Next Button
    @Composable
    fun NextButton(
        text: String,
        onClick: () -> Unit = {},
        background: Color,
        textColor: Color
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(background),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(61.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = text,
                    style = DessertTimeTheme.typography.textStyleRegular20,
                    color = textColor
                )
            }
        }
    }
}
