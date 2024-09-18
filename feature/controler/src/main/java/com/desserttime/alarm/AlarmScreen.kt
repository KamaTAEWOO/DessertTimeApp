package com.desserttime.alarm

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.Alto
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AlarmScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            AppBarUi.AppBar(
                {},
                title = stringResource(id = R.string.txt_alarm_title)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        top = paddingValues.calculateTopPadding(),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                        bottom = 0.dp // Remove the bottom padding
                    )
                    .background(WildSand)
            ) {
                // 여기서 알림이 있을 때와 없을 때 나누기
                Column(
                    modifier = Modifier.background(Color.White)
                ) {
                    AlarmScreenWithAlarm()
                }
            }
        }
    )
}

// 알림이 있을 때
@Composable
fun AlarmScreenWithAlarm() {
    // Row
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
            .background(Color.White)
    ) {
        // Box to overlay red dot on icon
        Box(
            modifier = Modifier
                .size(24.dp)
        ) {
            // Icon
            Image(
                painter = painterResource(R.drawable.ic_cash),
                contentDescription = stringResource(id = R.string.txt_alarm_title),
                modifier = Modifier
                    .size(24.dp)
            )

            // Red dot
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color.Red, shape = CircleShape)
                    .align(Alignment.TopEnd)
            )
        }

        // Text columns
        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            // Text Main
            Text(
                text = stringResource(id = R.string.txt_alarm_content_title),
                style = DessertTimeTheme.typography.textStyleBold16,
                color = Color.Black
            )

            // Text Sub
            Text(
                text = stringResource(id = R.string.txt_alarm_content_sub_title),
                style = DessertTimeTheme.typography.textStyleRegular16,
                color = Color.Black
            )

            // Text Time
            Text(
                text = stringResource(id = R.string.txt_alarm_content_date),
                style = DessertTimeTheme.typography.textStyleRegular12,
                color = Color.Gray
            )
        }
    }
}

// 알림이 없을 때
@Composable
fun AlarmScreenWithoutAlarm() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 200.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Icon
        Image(
            painter = painterResource(R.drawable.ic_alarm),
            contentDescription = stringResource(id = R.string.txt_alarm_title),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(12.dp),
            colorFilter = ColorFilter.tint(Alto)
        )
        // Text
        Text(
            text = stringResource(id = R.string.txt_alarm_not_description),
            color = Color.Gray,
            style = DessertTimeTheme.typography.textStyleRegular18,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlarmScreenPreview() {
    AlarmScreen()
}