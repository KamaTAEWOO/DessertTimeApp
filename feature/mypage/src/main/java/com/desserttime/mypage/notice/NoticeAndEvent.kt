package com.desserttime.mypage.notice

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.desserttime.design.R
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.domain.model.ContentDateData
import com.desserttime.mypage.MyPageViewModel

@Composable
fun NoticeAndEvent(
    myPageViewModel: MyPageViewModel
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            AppBarUi.AppBar(
                { },
                title = stringResource(id = R.string.txt_notice_event_title)
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
                NoticeAndEventContent(myPageViewModel)
            }
        }
    )
}

@Composable
fun NoticeAndEventContent(myPageViewModel: MyPageViewModel) {
    // 공지사항과 이벤트의 상태를 하나로 관리
    val myPageUiState by myPageViewModel.uiState.collectAsStateWithLifecycle()
    var isNoticeSelected by remember { mutableStateOf(myPageUiState.isNoticeAndEvent) } // 초기값을 true로 설정

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // 공지사항 Box
            Box(
                modifier = Modifier
                    .size(76.dp, 28.dp)
                    .background(
                        if (isNoticeSelected) MainColor else Color.Transparent, // 클릭 시 배경색 변경
                        shape = RoundedCornerShape(20.dp)
                    )
                    .border(
                        1.dp,
                        if (isNoticeSelected) MainColor else Color.Gray, // 클릭 시 테두리 색 변경
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable { isNoticeSelected = true }, // 클릭 시 공지사항을 선택하고 이벤트는 false
                contentAlignment = Alignment.Center // Box 내부에서 중앙 정렬
            ) {
                Text(
                    text = stringResource(R.string.txt_notice_event_notice),
                    color = if (isNoticeSelected) Color.White else Color.Gray,
                    style = DessertTimeTheme.typography.textStyleMedium14
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // 이벤트 Box
            Box(
                modifier = Modifier
                    .size(76.dp, 28.dp)
                    .background(
                        if (!isNoticeSelected) MainColor else Color.Transparent, // 클릭 시 배경색 변경
                        shape = RoundedCornerShape(20.dp)
                    )
                    .border(
                        1.dp,
                        if (!isNoticeSelected) MainColor else Color.Gray, // 클릭 시 테두리 색 변경
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable { isNoticeSelected = false }, // 클릭 시 이벤트를 선택하고 공지사항은 false
                contentAlignment = Alignment.Center // Box 내부에서 중앙 정렬
            ) {
                Text(
                    text = stringResource(R.string.txt_notice_event_event),
                    color = if (!isNoticeSelected) Color.White else Color.Gray,
                    style = DessertTimeTheme.typography.textStyleMedium14
                )
            }
        }

        // 여기에 다른 콘텐츠 추가
        if (isNoticeSelected) {
            NoticeContent()
        } else {
            EventContent()
        }
    }
}

@Composable
fun NoticeContent() {
    val noticeData = noticeData()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        items(noticeData.size) { item ->
            NoticeAndEventItem(noticeData[item].content, noticeData[item].date)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun EventContent() {
    val eventData = eventData()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        items(eventData.size) { item ->
            NoticeAndEventItem(eventData[item].content, eventData[item].date)
            Spacer(modifier = Modifier.height(8.dp)) // Adds space between items
        }
    }
}

@Composable
fun NoticeAndEventItem(
    content: String,
    date: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(73.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = content,
                style = DessertTimeTheme.typography.textStyleRegular14,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = date,
                style = DessertTimeTheme.typography.textStyleRegular12,
                color = Color.Gray
            )
        }
    }
}

fun noticeData(): List<ContentDateData> {
    val contentDateDataLists = listOf(
        ContentDateData(content = "버전 업데이트", date = "2024.04.19"),
        ContentDateData(content = "버전 업데이트", date = "2024.04.19")
    )
    return contentDateDataLists
}

fun eventData(): List<ContentDateData> {
    val contentDateDataLists = listOf(
        ContentDateData(content = "출석체크하고 곳간 채우기!", date = "2024.04.19"),
        ContentDateData(content = "출석체크하고 곳간 채우기!", date = "2024.04.19")
    )
    return contentDateDataLists
}
