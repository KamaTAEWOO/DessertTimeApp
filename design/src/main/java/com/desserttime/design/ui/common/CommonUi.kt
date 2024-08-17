package com.desserttime.design.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
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
        paddingVertical: Dp = 2.dp,
        modifier: Modifier = Modifier
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(containerColor)
                .border(0.dp, Color.Transparent)
                .shadow(0.dp, RectangleShape)
        ) {
            BasicTextField(
                value = textFieldValue,
                onValueChange = onValueChange,
                textStyle = textStyle,
                cursorBrush = SolidColor(cursorColor),
                singleLine = true, // Restrict input to a single line
                decorationBox = { innerTextField ->
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, top = 10.dp, bottom = 10.dp)
                        ) {
                            if (textFieldValue.text.isEmpty()) {
                                Text(
                                    text = placeholderText,
                                    style = placeholderStyle,
                                    color = Black30
                                )
                            }
                            innerTextField() // This renders the actual text
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(underlineThickness)
                                .background(
                                    if (textFieldValue.text.isEmpty()) {
                                        unfocusedIndicatorColor
                                    } else {
                                        focusedIndicatorColor
                                    }
                                )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    @Composable
    fun Divide(color: Color) {
        Divider(
            color = color,
            thickness = 1.dp
        )
    }
}
