package com.desserttime.design.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.desserttime.design.theme.Black30
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomTextField(
        textFieldValue: TextFieldValue,
        onValueChange: (TextFieldValue) -> Unit,
        placeholderText: String,
        placeholderStyle: TextStyle = TextStyle(),
        containerColor: Color = Color.Transparent,
        cursorColor: Color = Color.Gray,
        focusedIndicatorColor: Color = Color.Gray,
        unfocusedIndicatorColor: Color = Color.Gray,
        textStyle: TextStyle = TextStyle(),
        underlineThickness: Dp = 1.dp,
        paddingVertical: Dp = 2.dp, // 텍스트와 언더라인 사이의 간격을 조정하기 위한 패딩
        modifier: Modifier = Modifier
    ) {
        val textFieldColors = TextFieldDefaults.textFieldColors(
            containerColor = containerColor, // TextField의 배경색
            cursorColor = cursorColor, // 커서 색상
            focusedIndicatorColor = focusedIndicatorColor, // 포커스 시 언더라인 색상
            unfocusedIndicatorColor = unfocusedIndicatorColor // 포커스가 없을 때 언더라인 색상
        )

        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White) // 배경을 명시적으로 흰색으로 설정
                .border(0.dp, Color.Transparent)
                .shadow(0.dp, RectangleShape)
        ) {
            TextField(
                value = textFieldValue,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = placeholderText,
                        style = placeholderStyle,
                        color = Black30, // Placeholder 색상
                        modifier = Modifier.wrapContentSize()
                    )
                },
                colors = textFieldColors,
                textStyle = textStyle,
                modifier = modifier
                    .padding(bottom = paddingVertical) // 텍스트와 언더라인 사이의 간격 조절
                    .background(Color.White) // 흰색 배경 설정
                    .border(BorderStroke(underlineThickness, Color.Black), RoundedCornerShape(0.dp))
            )
        }
    }
}
