package com.desserttime.mypage.wheat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.MaiTai
import com.desserttime.design.theme.TundoraCategory
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.domain.model.WheatDetailData

@Composable
fun WheatScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            AppBarUi.AppBar(
                {},
                title = stringResource(id = R.string.txt_mypage_mileage),
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
                Spacer(modifier = Modifier.height(8.dp))
                // main wheat content
                WheatContent()
                Spacer(modifier = Modifier.height(20.dp))
                // detail wheat content
                WheatDetailContent()
            }
        }
    )
}

@Composable
fun WheatContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 24.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp)) // 전체 화이트 상자
            .padding(16.dp) // 내부 여백 줄이기
    ) {
        // 첫 번째 Row: main wheat content
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.txt_wheat_storeroom),
                    color = TundoraCategory,
                    style = DessertTimeTheme.typography.textStyleMedium16,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 8.dp) // 여백 줄이기
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp, start = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cash),
                        contentDescription = stringResource(id = R.string.txt_mypage_mileage),
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 8.dp) // 아이콘 살짝 아래로 내림
                    )
                    Text(
                        text = stringResource(id = R.string.txt_mypage_mileage_count),
                        color = TundoraCategory,
                        style = DessertTimeTheme.typography.textStyleBold26,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }

            // 오른쪽 이미지
            Image(
                painter = painterResource(id = R.drawable.ic_sack),
                contentDescription = stringResource(id = R.string.txt_mypage_mileage),
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White)
                    .clip(RoundedCornerShape(0.dp))
                    .shadow(0.dp)
                    .background(Color.Transparent)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(WildSand, shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp) // 패딩 조정
        ) {
            Text(
                text = stringResource(id = R.string.txt_wheat_month_fill),
                color = TundoraCategory,
                style = DessertTimeTheme.typography.textStyleMedium14,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp) // 우측 여백 추가
            )

            Text(
                text = stringResource(id = R.string.txt_wheat_month_fill_count),
                color = Color.Black,
                style = DessertTimeTheme.typography.textStyleMedium18,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp) // 좌측 여백 추가
            )
        }
    }
}

@Composable
fun WheatDetailContent() {
    // detail wheat content - wheat 사용 내역
    val wheatDetailData = loadData()
    // wheat 사용 내역
    Text(
        text = stringResource(id = R.string.txt_wheat_detail),
        color = TundoraCategory,
        style = DessertTimeTheme.typography.textStyleMedium16,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(start = 24.dp)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        // list of wheat detail
        wheatDetailData.forEach { wheatDetailData ->
            WheatDetailItem(wheatDetailData)
        }
    }
}

@Composable
fun WheatDetailItem(wheatDetailData: WheatDetailData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(73.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Text(
                        text = wheatDetailData.name,
                        style = DessertTimeTheme.typography.textStyleBold16,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp)) // Optional space between texts
                    Text(
                        text = stringResource(R.string.txt_wheat_detail_behind),
                        style = DessertTimeTheme.typography.textStyleRegular16,
                        color = Color.Black
                    )
                }
                Text(
                    text = wheatDetailData.price.toString() + stringResource(R.string.txt_wheat_price),
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    color = MaiTai
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = wheatDetailData.date,
                    style = DessertTimeTheme.typography.textStyleRegular10,
                    color = TundoraCategory
                )
                Text(
                    text = stringResource(R.string.txt_wheat_detail_save),
                    style = DessertTimeTheme.typography.textStyleRegular10,
                    color = TundoraCategory
                )
            }
        }
    }
}

fun loadData(): List<WheatDetailData> {
    // WheatDetailData 리스트로 담기
    val wheatDetailDataList = listOf(
        WheatDetailData(name = "바질치즈 스콘", price = 4, date = "2024.04.19"),
        WheatDetailData(name = "바질치즈 스콘", price = 4, date = "2024.04.19"),
        WheatDetailData(name = "바질치즈 스콘", price = 4, date = "2024.04.19"),
        WheatDetailData(name = "바질치즈 스콘", price = 4, date = "2024.04.19"),
        WheatDetailData(name = "바질치즈 스콘", price = 4, date = "2024.04.19"),
        WheatDetailData(name = "바질치즈 스콘", price = 4, date = "2024.04.19"),
        WheatDetailData(name = "바질치즈 스콘", price = 4, date = "2024.04.19"),
        WheatDetailData(name = "바질치즈 스콘", price = 4, date = "2024.04.19"),
        WheatDetailData(name = "바질치즈 스콘", price = 4, date = "2024.04.19"),
        WheatDetailData(name = "바질치즈 스콘", price = 4, date = "2024.04.19")
    )
    return wheatDetailDataList
}
