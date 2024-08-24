package com.desserttime.design.ui.common

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import coil.compose.rememberImagePainter
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.DustyGray
import com.desserttime.design.theme.WildSand
import timber.log.Timber
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
    fun BirthYearDropdown(
        expanded: Boolean,
        selectedYear: String,
        onYearSelected: (String) -> Unit,
        onDismiss: () -> Unit
    ) {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val birthYears = (1950..currentYear).toList()
        // LazyColumn의 스크롤 상태를 추적하기 위한 상태
        val listState = rememberLazyListState()

        if (expanded) {
            Popup(
                onDismissRequest = onDismiss,
                alignment = Alignment.BottomCenter,
                offset = IntOffset(0, 150)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .padding(8.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    LaunchedEffect(selectedYear) {
                        // 선택된 연도로 스크롤 이동
                        val selectedIndex = birthYears.indexOf(selectedYear.toIntOrNull())
                        if (selectedIndex != -1) {
                            listState.animateScrollToItem(selectedIndex)
                        }
                        Timber.i("selectedIndex: $selectedIndex")
                    }

                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 250.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(birthYears) { year ->
                            val isSelected = (year.toString() == selectedYear)
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .background(
                                        color = if (isSelected) WildSand else Color.Transparent,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clickable {
                                        onYearSelected(year.toString())
                                        onDismiss()
                                    }
                            ) {
                                Text(
                                    text = year.toString(),
                                    color = if (isSelected) Color.Black else Color.Gray,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    style = if (isSelected) DessertTimeTheme.typography.textStyleBold18 else DessertTimeTheme.typography.textStyleRegular16,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
