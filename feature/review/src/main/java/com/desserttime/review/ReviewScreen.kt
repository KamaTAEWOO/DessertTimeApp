package com.desserttime.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.ui.common.AppBarUi

@Composable
fun ReviewScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AppBarUi.AppBar(
            {},
            stringResource(id = com.desserttime.design.R.string.txt_bottom_review),
            {},
            {}
        )
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
        ) {
            // 작성 가능 후기

            // 6건
            // 22일 남았어요!
            // icon
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        LazyColumn {
            // items -> ReviewItem
        }
        // 영수증 등록하기 버튼은 하단에 고정하고 위에 떠있어야함.
    }
}

@Composable
fun ReviewItem() {
    Row {
        Column {
            // Text
            // Text
        }
        // Image -> 클릭 시 다음 화면 전환 / item의 id를 넘겨줘야함.
        // Image -> 클릭 시 삭제하기 팝업창 Image 위에나 아래에 떠야함.
    }
}

@Preview(showBackground = true)
@Composable
fun WritingReviewScreenPreview() {
    ReviewScreen()
}
