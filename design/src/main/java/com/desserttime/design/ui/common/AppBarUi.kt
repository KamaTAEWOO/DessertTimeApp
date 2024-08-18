package com.desserttime.design.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.DessertTimeTheme

object AppBarUi {

    // *********************************************************************************************
    // *************************************Public Method*******************************************
    // *********************************************************************************************

    // AppBar - 매개변수(로고, 돋보기, 벨)
    @Composable
    fun AppBar(
        onSearchClick: () -> Unit = {},
        onBellClick: () -> Unit = {}
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.White)
                .padding(horizontal = 16.dp), // Added padding to align items nicely
            verticalAlignment = Alignment.CenterVertically, // Align items vertically centered
            horizontalArrangement = Arrangement.SpaceBetween // Space items evenly with space between
        ) {
            // 로고 이미지
            Image(
                painter = painterResource(id = R.drawable.ic_appbar_logo),
                contentDescription = stringResource(id = R.string.img_login_logo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(height = 28.dp, width = 216.dp)
                    .padding(2.dp)
            )

            Row {
                // 검색 이미지
                IconButton(onClick = onSearchClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_appbar_search),
                        contentDescription = stringResource(id = R.string.txt_search_description)
                    )
                }
                // 벨 이미지
                IconButton(onClick = onBellClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_appbar_bell),
                        contentDescription = stringResource(id = R.string.txt_bell_description)
                    )
                }
            }
        }
    }

    @Composable
    fun AppBar(
        title: String,
        onSearchClick: () -> Unit = {},
        onBellClick: () -> Unit = {}
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.White)
                .padding(horizontal = 16.dp)
        ) {
            // 중앙에 닉네임 텍스트
            Text(
                text = title,
                style = DessertTimeTheme.typography.textStyleBold20,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center) // Center both vertically and horizontally
            )

            // 오른쪽에 검색 및 벨 아이콘
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                // 검색 이미지
                IconButton(onClick = onSearchClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_appbar_search),
                        contentDescription = stringResource(id = R.string.txt_search_description)
                    )
                }
                // 벨 이미지
                IconButton(onClick = onBellClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_appbar_bell),
                        contentDescription = stringResource(id = R.string.txt_bell_description)
                    )
                }
            }
        }
    }

    @Composable
    fun AppBar(
        onBackClick: () -> Unit = {},
        title: String,
        onSearchClick: () -> Unit = {},
        onBellClick: () -> Unit = {}
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.White)
                .padding(horizontal = 16.dp)
        ) {
            // 뒤로가기 이미지
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = stringResource(id = R.string.txt_previous)
                )
            }

            // 타이틀 중앙 배치
            Text(
                text = title,
                style = DessertTimeTheme.typography.textStyleBold20,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )

            // 오른쪽에 검색 및 벨 아이콘
            Row(
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                // 검색 이미지
                IconButton(onClick = onSearchClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_appbar_search),
                        contentDescription = stringResource(id = R.string.txt_search_description)
                    )
                }
                // 벨 이미지
                IconButton(onClick = onBellClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_appbar_bell),
                        contentDescription = stringResource(id = R.string.txt_bell_description)
                    )
                }
            }
        }
    }

    @Composable
    fun AppBar(
        onBackClick: () -> Unit = {},
        title: String
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.White)
                .padding(horizontal = 16.dp)
        ) {
            // 뒤로가기 이미지
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = stringResource(id = R.string.txt_previous)
                )
            }

            // 타이틀 중앙 배치
            Text(
                text = title,
                style = DessertTimeTheme.typography.textStyleBold20,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    @Composable
    fun AppBar(
        title: String,
        onSettingClick: () -> Unit = {}
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.White)
                .padding(horizontal = 16.dp)
        ) {
            // 타이틀 중앙 배치
            Text(
                text = title,
                style = DessertTimeTheme.typography.textStyleBold20,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
            // setting 이미지
            IconButton(
                onClick = onSettingClick,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = stringResource(id = R.string.img_mypage_setting)
                )
            }
        }
    }

    @Composable
    fun AppBar(
        onBackClick: () -> Unit = {},
        title: String,
        onSaveClick: () -> Unit = {},
        color: Color
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = Color.White)
                .padding(horizontal = 16.dp)
        ) {
            // 뒤로가기 이미지
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = stringResource(id = R.string.txt_previous)
                )
            }
            // 타이틀 중앙 배치
            Text(
                text = title,
                style = DessertTimeTheme.typography.textStyleBold20,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
            // save 버튼을 오른쪽 상단에 배치
            Text(
                text = stringResource(id = R.string.txt_save),
                style = DessertTimeTheme.typography.textStyleRegular18,
                color = color,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clickable { onSaveClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppBarPreview() {
    AppBarUi.AppBar("카테고리", {}, {})
}
