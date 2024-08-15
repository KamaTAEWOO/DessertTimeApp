package com.desserttime.like

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.Alabaster
import com.desserttime.design.theme.Alto
import com.desserttime.design.theme.Black60
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.DustyGray
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.Tundora50
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.like.model.LikeData

@Composable
fun LikeDetailScreen(
    onNavigateToLike: () -> Unit
) {
    Scaffold(
        modifier = Modifier.padding(top = 26.dp),
        topBar = {
            AppBarUi.AppBar(
                { onNavigateToLike() },
                title = stringResource(id = R.string.txt_like_detail_title),
                {},
                {}
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // 시스템 패딩을 적용하여 AppBar와의 간격 유지
                    .background(Color.White)
            ) {
                // LikeDetailItem
                LikeDetailItem(
                    LikeData.icLikeProfile,
                    stringResource(id = LikeData.nickName),
                    stringResource(id = LikeData.date),
                    LikeData.likeCount,
                    LikeData.title,
                    LikeData.score,
                    LikeData.likePicture,
                    stringResource(id = LikeData.content),
                    LikeData.materialArr
                )
            }
        }
    )
}

@Composable
fun LikeDetailItem(
    icLikeProfile: Int,
    nickName: String,
    date: String,
    likeCount: Int,
    title: Int,
    score: Int,
    likePicture: Int,
    content: String,
    materialArr: List<Int>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 18.dp)
        ) {
            Image(
                painter = painterResource(id = icLikeProfile),
                contentDescription = stringResource(id = R.string.img_like_nickname),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = nickName,
                    style = DessertTimeTheme.typography.textStyleRegular12,
                    color = Color.Black
                )
                Text(
                    text = date,
                    style = DessertTimeTheme.typography.textStyleRegular12,
                    color = DustyGray
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_like),
                    contentDescription = stringResource(id = R.string.img_like_love),
                    modifier = Modifier
                        .size(20.dp)
                        .padding(end = 4.dp)
                )
                Text(
                    text = stringResource(id = likeCount),
                    style = DessertTimeTheme.typography.textStyleBold12,
                    color = MainColor,
                    modifier = Modifier.padding(top = 3.dp, end = 3.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 20.dp, bottom = 20.dp)
        ) {
            MenuDetailPicture(likePicture)
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                text = stringResource(id = title),
                style = DessertTimeTheme.typography.textStyleBold18,
                color = Color.Black
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            ScoreCheck(score)
            Spacer(modifier = Modifier.padding(top = 16.dp))
            Text(
                text = content,
                style = DessertTimeTheme.typography.textStyleRegular12,
                color = Black60,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            MaterialItemList(materialArr)
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Divider(
                color = Alabaster,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(top = 12.dp))
            ReportButton()
        }
    }
}

@Composable
fun MenuDetailPicture(likePicture: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(192.dp)
                .clip(RoundedCornerShape(9.dp))
                .background(WildSand)
                .border(1.dp, Alto, RoundedCornerShape(9.dp))
        ) {
            Image(
                painter = painterResource(id = likePicture),
                contentDescription = stringResource(id = R.string.img_review_write_menu_image_description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(9.dp))
            )
        }
    }
}

// 신고하기 버튼
// 오른쪽 끝으로 정렬
@Composable
fun ReportButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.txt_like_report),
            style = DessertTimeTheme.typography.textStyleRegular12,
            color = Tundora50,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}
