package com.desserttime.mypage.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.GrayChateau
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.Salem
import com.desserttime.design.theme.Salem20
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi

@Composable
fun MyReviewScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            AppBarUi.AppBar(
                {},
                title = stringResource(id = R.string.txt_my_review_title),
                {},
                {}
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
                MyReviewItem()
            }
        }
    )
}

// Item
@Composable
fun MyReviewItem() {
    var showDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .background(Color.White)
    ) {
        // Review Image
        Image(
            painter = painterResource(id = R.drawable.ic_cake_review1),
            contentDescription = stringResource(id = R.string.txt_my_review_title_image),
            modifier =Modifier
                .size(108.dp, 92.dp)
                .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                .background(Color.Red),
            contentScale = ContentScale.Fit
        )

        // Review Details (Title, Subtitle, Rating)
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = "온혜화",
                style = DessertTimeTheme.typography.textStyleRegular14,
                color = Color.Black
            )
            Text(
                text = "바질치즈스콘",
                style = DessertTimeTheme.typography.textStyleRegular16,
                color = Color.Black,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            )
            ScoreCheck() // Rating Component
        }

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.End // Align all content to the end (right)
        ) {
            // Box at the top aligned to the end
            Box(
                modifier = Modifier
                    .size(56.dp, 20.dp)
                    .padding(end = 20.dp)
                    .background(Salem20, RoundedCornerShape(50.dp))
            ) {
                Text(
                    text = stringResource(id = R.string.txt_my_review_resister),
                    style = DessertTimeTheme.typography.textStyleLight10,
                    color = Salem,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50.dp))
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }

            // Spacer to push IconButton to the bottom
            Spacer(modifier = Modifier.weight(1f))

            // IconButton at the bottom aligned to the end
            IconButton(onClick = { showDialog = true }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_review_delete),
                    contentDescription = stringResource(id = R.string.txt_review_delete)
                )
            }
        }
    }
}

@Composable
fun ScoreCheck() {
    val starStates = remember { mutableStateListOf(false, false, false, false) }

    Row(
        modifier = Modifier.wrapContentSize(Alignment.CenterStart)
    ) {
        repeat(4) { index ->
            val imageResource = if (starStates[index]) {
                painterResource(id = R.drawable.ic_star_on) // 클릭되었을 때의 이미지
            } else {
                painterResource(id = R.drawable.ic_star_off) // 기본 이미지
            }

            Image(
                painter = imageResource,
                contentDescription = stringResource(id = R.string.img_review_write_score_description),
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(20.dp)
            )
        }
    }
}