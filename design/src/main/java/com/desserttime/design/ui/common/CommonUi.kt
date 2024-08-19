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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.DessertTimeTheme
import java.util.Calendar

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

    @Composable
    fun BirthYearDropdown(expanded: Boolean, selectedYear: String, onYearSelected: (String) -> Unit, onDismiss: () -> Unit) {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val birthYears = (1900..currentYear).toList()

        // Popup 사용하여 DropdownMenu를 팝업으로 표시
        if (expanded) {
            Popup(
                onDismissRequest = onDismiss,
                alignment = Alignment.BottomCenter
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .padding(16.dp),
                    color = Color.White,  // 배경 색상 화이트 설정
                    shape = RoundedCornerShape(8.dp)
                ) {
                    LazyColumn( // 스크롤 가능한 Column
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 300.dp),  // 최대 높이 설정, 스크롤 가능
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(birthYears) { year ->
                            val isSelected = (year.toString() == selectedYear)
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = year.toString(),
                                        color = if (isSelected) Color.Black else Color.Gray,
                                        modifier = Modifier.fillMaxWidth(),
                                        style = DessertTimeTheme.typography.textStyleRegular16,
                                        textAlign = TextAlign.Center  // 텍스트 중앙 정렬
                                    )
                                },
                                onClick = {
                                    onYearSelected(year.toString())  // 선택된 연도를 콜백으로 전달
                                    onDismiss()  // 선택 후 팝업 닫기
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }

//    @Composable
//    fun BirthYearDropdown(expanded: Boolean, onYearSelected: (String) -> Unit) {
//        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
//        val birthYears = (1990..currentYear).toList()
//
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            birthYears.forEach { year ->
//                DropdownMenuItem(
//                    text = { Text(text = year.toString()) },
//                    onClick = {
//                        onYearSelected(year.toString())  // 선택된 연도를 콜백으로 전달
//                    }
//                )
//            }
//        }
//    }
}
