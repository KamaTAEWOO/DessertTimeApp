package com.desserttime.design.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.desserttime.design.theme.AzureRadiance
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.MainColor

object PopUpUi {

    // *********************************************************************************************
    // *************************************Public Method*******************************************
    // *********************************************************************************************

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

    // 에러 / 중복 팝업
    @Composable
    fun AlertPopup(
        title: String,
        content: String,
        buttonText: String,
        onConfirm: () -> Unit
    ) {
        Popup(
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
                    Spacer(modifier = Modifier.height(22.dp))

                    // 타이틀 섹션
                    Text(
                        text = title,
                        style = DessertTimeTheme.typography.textStyleRegular16,
                        color = Color.Black,
                        textAlign = TextAlign.Center, // 텍스트를 중앙으로 정렬
                        modifier = Modifier
                            .fillMaxWidth() // 전체 가로 폭을 차지하여 중앙 정렬이 적용되도록 설정
                            .align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = content,
                        style = DessertTimeTheme.typography.textStyleRegular16,
                        color = Color.Black,
                        textAlign = TextAlign.Center, // 텍스트를 중앙으로 정렬
                        modifier = Modifier
                            .fillMaxWidth() // 전체 가로 폭을 차지하여 중앙 정렬이 적용되도록 설정
                            .padding(vertical = 14.dp) // iOS 느낌의 여백
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
                        TextButton(
                            onClick = onConfirm,
                            modifier = Modifier.weight(1f), // 버튼이 같은 비율로 크기를 차지하도록 설정
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = AzureRadiance
                            )
                        ) {
                            Text(
                                text = buttonText,
                                color = AzureRadiance,
                                style = DessertTimeTheme.typography.textStyleRegular16,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
    }

    // Page Error
}