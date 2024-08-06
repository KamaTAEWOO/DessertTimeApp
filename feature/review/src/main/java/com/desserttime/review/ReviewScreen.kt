package com.desserttime.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.desserttime.design.R
import com.desserttime.design.theme.Black60
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.TundoraCategory
import com.desserttime.design.theme.WildSand
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
            stringResource(id = R.string.txt_bottom_review),
            {},
            {}
        )
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
            .background(WildSand))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp) // Fixed height to prevent stretching
                .background(WildSand)
        ) {
            // 작성 가능 후기
            Text(
                text = stringResource(id = R.string.txt_review_possibility),
                style = DessertTimeTheme.typography.textStyleBold18,
                color = Color.Black,
                modifier = Modifier
                    .padding(start = 20.dp, end = 8.dp)
            )
            // 6건
            Box(modifier = Modifier
                .padding(top = 4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.txt_review_possibility_count),
                    style = DessertTimeTheme.typography.textStyleMedium12,
                    color = Color.White,
                    modifier = Modifier
                        .background(MainColor, shape = RoundedCornerShape(20.dp))
                        .clip(RoundedCornerShape(20.dp))
                        .size(38.dp, 18.dp),
                    textAlign = TextAlign.Center
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, end = 20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_review_leave_days),
                        style = DessertTimeTheme.typography.textStyleMedium12,
                        color = TundoraCategory,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .size(14.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_alert_review),
                            contentDescription = stringResource(id = R.string.img_review_leave_days_info_description)
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // Main content with review items
            ReviewItemView(
                modifier = Modifier
                    .fillMaxSize()
                    .background(WildSand)
                    .padding(horizontal = 12.dp)
            )
            // Floating button positioned at the bottom
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .zIndex(1f)
            ) {
                Button(
                    onClick = { /* TODO: Handle click */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainColor,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .height(61.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_review_new_receipt),
                        style = DessertTimeTheme.typography.textStyleMedium20,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ReviewItemView(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(10) {
            ReviewItem()
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(WildSand))
        }
    }
}

@Composable
fun ReviewItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 28.dp, top = 17.dp)
        ) {
            // Text
            Text(
                text = stringResource(id = R.string.txt_review_test_id),
                style = DessertTimeTheme.typography.textStyleRegular16,
                color = Black60
            )
            // Text
            Text(
                text = stringResource(id = R.string.txt_review_test_title),
                style = DessertTimeTheme.typography.textStyleRegular16,
                color = TundoraCategory,
                modifier = Modifier.padding(top = 4.dp, bottom = 17.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterEnd)
            ) {
                // Image -> 클릭 시 다음 화면 전환 / item의 id를 넘겨줘야함.
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_review_write),
                        contentDescription = stringResource(id = R.string.img_review_write_description),
                        tint = Color.Gray
                    )
                }
                // Image -> 클릭 시 삭제하기 팝업창 Image 위에나 아래에 떠야함.
                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_review_delete),
                        contentDescription = stringResource(id = R.string.txt_review_delete)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WritingReviewScreenPreview() {
    ReviewScreen()
}
