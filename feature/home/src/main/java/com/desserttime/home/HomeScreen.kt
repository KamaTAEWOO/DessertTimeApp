package com.desserttime.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.ui.common.AppBarUi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // AppBar
        AppBarUi.AppBar()
        // 배너
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            AutoScrollingBanner(
                imageResIds = listOf(
                    R.drawable.ic_banner1,
                    R.drawable.ic_banner2
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AutoScrollingBanner(
    imageResIds: List<Int>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    // 자동 스크롤 설정
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000) // 3초 대기
            coroutineScope.launch {
                val nextPage = (pagerState.currentPage + 1) % imageResIds.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp) // 배너의 높이를 설정
    ) {
        HorizontalPager(
            count = imageResIds.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Image(
                painter = painterResource(id = imageResIds[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            )
        }

        // 페이지 인디케이터를 이미지 위에 오버레이
        PagerIndicator(pagerState = pagerState, modifier = Modifier
            .align(Alignment.BottomCenter) // 인디케이터를 하단 중앙에 배치
            .padding(bottom = 16.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerIndicator(pagerState: PagerState, modifier: Modifier = Modifier) {
    val pageCount = pagerState.pageCount
    val currentPage = pagerState.currentPage

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp), // 인디케이터 간의 간격 설정
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            IndicatorDot(isActive = index == currentPage)
        }
    }
}

@Composable
fun IndicatorDot(isActive: Boolean) {
    val color = if (isActive) Color.Blue else Color.DarkGray

    Box(
        modifier = Modifier
            .size(8.dp)
            .background(color, shape = CircleShape)
            .padding(4.dp)
    )
}

// 서버에서 받은 이미지 뿌릴 때 사용
// 사용 방법
// Surface(modifier = Modifier.fillMaxSize()) {
//            AutoScrollingBanner(
//                imageUrls = listOf(
//                    "https://example.com/image1.jpg",
//                    "https://example.com/image2.jpg",
//                    "https://example.com/image3.jpg"
//                ),
//                modifier = Modifier.fillMaxWidth()
//            )
//        }
// @Composable
// fun AutoScrollingBanner(
//    imageUrls: List<String>,
//    modifier: Modifier = Modifier
// ) {
//    val coroutineScope = rememberCoroutineScope()
//    var currentIndex by remember { mutableStateOf(0) }
//
//    LaunchedEffect(key1 = currentIndex) {
//        coroutineScope.launch {
//            delay(3000) // 3초 대기
//            currentIndex = (currentIndex + 1) % imageUrls.size
//        }
//    }
//
//    LazyRow(
//        modifier = modifier
//    ) {
//        itemsIndexed(imageUrls) { index, imageUrl ->
//            Image(
//                painter = rememberImagePainter(data = imageUrl),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//            )
//        }
//    }
// }

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
