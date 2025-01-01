package com.desserttime.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.desserttime.core.utility.MemberDataManager
import com.desserttime.design.R
import com.desserttime.design.theme.CornflowerBlue
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.MainColor
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.home.model.ReviewSectionData
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import androidx.compose.foundation.lazy.items

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onNavigateToLogin: () -> Unit,
    onNavigateToAlarm: () -> Unit
) {
    LaunchedEffect("onec") {
        homeViewModel.requestMemberData(MemberDataManager.memberId.toString())
    }
    val memberId = MemberDataManager.memberId ?: 0
    val nickname = remember { mutableStateOf("") }
    val homeUIState by homeViewModel.uiState.collectAsState()

    LaunchedEffect(nickname) {
        nickname.value = homeViewModel.memberData.first().nickName
    }

    Timber.d("ImageData: ${homeUIState.reviewImageData}")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            AppBarUi.AppBar(
                memberId = memberId,
                memberNickName = nickname.value,
                onSearchClick = { /* 검색 버튼 클릭 시 동작 */ },
                onBellClick = {
                    if (memberId == 0) {
                        onNavigateToLogin()
                    } else {
                        onNavigateToAlarm()
                    }
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            BannerSection(
                imageResIds = listOf(
                    R.drawable.ic_banner1,
                    R.drawable.ic_banner2,
                    R.drawable.ic_banner1,
                    R.drawable.ic_banner2
                )
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        val reviewSections = listOf(
            ReviewSectionData(
                titleResId = R.string.txt_home_review_title_test1,
                imageResIds = homeUIState.reviewImageData
            ),
//            ReviewSectionData(
//                titleResId = R.string.txt_home_review_title_test2,
//                imageResIds = listOf(
//                    R.drawable.ic_bingsu_review1,
//                    R.drawable.ic_bingsu_review2,
//                    R.drawable.ic_bingsu_review3,
//                    R.drawable.ic_bingsu_review1,
//                    R.drawable.ic_bingsu_review2,
//                    R.drawable.ic_add_review
//                )
//            ),
//            ReviewSectionData(
//                titleResId = R.string.txt_home_review_title_test3,
//                imageResIds = listOf(
//                    R.drawable.ic_bingsu_review1,
//                    R.drawable.ic_bingsu_review2,
//                    R.drawable.ic_bingsu_review3,
//                    R.drawable.ic_bingsu_review1,
//                    R.drawable.ic_bingsu_review2,
//                    R.drawable.ic_add_review
//                )
//            )
        )

        reviewSections.forEach { section ->
            item {
                ReviewSection(
                    titleResId = section.titleResId,
                    imageUrls = section.imageResIds
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun BannerSection(
    imageResIds: List<Int>,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        AutoScrollingBanner(
            imageResIds = imageResIds,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        )
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

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            coroutineScope.launch {
                val nextPage = (pagerState.currentPage + 1) % imageResIds.size
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
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

        PagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val pageCount = pagerState.pageCount
    val currentPage = pagerState.currentPage

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            IndicatorDot(isActive = index == currentPage)
        }
    }
}

@Composable
fun IndicatorDot(isActive: Boolean) {
    val color = if (isActive) MainColor else CornflowerBlue

    Box(
        modifier = Modifier
            .size(8.dp)
            .background(color, shape = CircleShape)
    )
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ReviewSection(
    titleResId: Int,
    imageUrls: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = stringResource(id = titleResId),
            style = DessertTimeTheme.typography.textStyleBold18,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(imageUrls) { imageUrl ->
                Timber.d("imageUrl: $imageUrl")
                GlideImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(width = 106.dp, height = 140.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }

    }
}

