package com.desserttime.design.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Device
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.desserttime.design.R
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.WildSand
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
        textColor: Color,
        enabled: Boolean
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                background,
                disabledContainerColor = background
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(61.dp),
            enabled = enabled
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
        val birthYears = (1930..currentYear).toList()
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
                        val selectedIndex = birthYears.indexOf(selectedYear.toIntOrNull())
                        if (selectedIndex != -1) {
                            // 선택된 항목을 중앙보다 더 아래로 스크롤
                            val scrollOffset = -250 // 원하는 만큼의 오프셋을 설정해 선택 항목을 아래로 내림
                            listState.animateScrollToItem(selectedIndex, scrollOffset)
                        }
                    }

                    // 화면 중앙에 있는 항목의 인덱스 계산
                    val centerIndex by remember {
                        derivedStateOf {
                            val firstVisibleIndex = listState.firstVisibleItemIndex
                            val viewportHeight = listState.layoutInfo.viewportEndOffset
                            val visibleItems = listState.layoutInfo.visibleItemsInfo
                            if (visibleItems.isNotEmpty()) {
                                val firstItemOffset = visibleItems.first().offset
                                val lastItemOffset = visibleItems.last().offset + visibleItems.last().size
                                val centerOffset = (viewportHeight / 2)
                                visibleItems.indexOfFirst {
                                    (it.offset <= centerOffset && it.offset + it.size >= centerOffset)
                                } + firstVisibleIndex
                            } else {
                                -1 // 리스트가 비어있을 때 처리
                            }
                        }
                    }

                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 250.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        itemsIndexed(birthYears) { index, year ->
                            val isSelected = (year.toString() == selectedYear)
                            val isCenter = index == centerIndex // 중앙에 위치한 항목인지 여부
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .background(
                                        color = if (isCenter) WildSand else Color.Transparent,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clickable {
                                        onYearSelected(year.toString()) // 선택된 연도 업데이트
                                        onDismiss() // 팝업 닫기
                                    }
                            ) {
                                Text(
                                    text = year.toString(),
                                    color = when {
                                        isCenter -> Color.Black // 중앙에 있는 항목은 항상 진하게
                                        isSelected -> Color.Gray // 선택된 항목은 Gray로 되돌림
                                        else -> Color.Gray // 나머지는 기본 회색
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    style = if (isCenter) DessertTimeTheme.typography.textStyleBold18 else DessertTimeTheme.typography.textStyleRegular16,
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

@Composable
fun CommonPopup(
    title: String, // 팝업 제목
    onConfirm: () -> Unit, // "삭제하기" 버튼 클릭 시 호출되는 콜백
    onDismiss: () -> Unit, // "아니요" 버튼 클릭 시 호출되는 콜백
    onPopupDismiss: () -> Unit // 팝업 외부 영역 클릭 시 호출되는 콜백
) {
    Popup(
        onDismissRequest = onPopupDismiss,
        alignment = Alignment.Center
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp), // 모서리를 둥글게 설정
            color = Color.White,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(16.dp)) // 전체 모서리를 iOS 스타일로 둥글게 설정
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 타이틀 섹션
                Text(
                    text = title,
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    color = Color.Black,
                    textAlign = TextAlign.Center, // 텍스트를 중앙으로 정렬
                    modifier = Modifier
                        .fillMaxWidth() // 전체 가로 폭을 차지하여 중앙 정렬이 적용되도록 설정
                        .padding(vertical = 24.dp) // iOS 느낌의 여백
                        .align(Alignment.CenterHorizontally)
                )

                // 버튼들 사이의 회색 선
                Divider(color = Color.LightGray, thickness = 1.dp)

                // 버튼 섹션
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp), // iOS 스타일 버튼 높이
                    horizontalArrangement = Arrangement.Center
                ) {
                    // "삭제하기" Button
                    TextButton(
                        onClick = onConfirm,
                        modifier = Modifier.weight(1f), // 버튼이 같은 비율로 크기를 차지하도록 설정
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MainColor
                        )
                    ) {
                        Text(
                            text = "삭제하기",
                            color = MainColor,
                            style = DessertTimeTheme.typography.textStyleRegular16,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

                    // 세로로 된 분리선 (버튼 사이)
                    Divider(
                        color = Color.LightGray,
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )

                    // "아니요" Button
                    TextButton(
                        onClick = onDismiss,
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = Color.Gray
                        )
                    ) {
                        Text(
                            text = "아니요",
                            color = Color.Gray,
                            style = DessertTimeTheme.typography.textStyleRegular16,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}
