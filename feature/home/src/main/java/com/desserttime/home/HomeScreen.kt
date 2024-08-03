import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.CornflowerBlue
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.MainColor
import com.desserttime.design.ui.common.AppBarUi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(onNavigateToLogin: () -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            // AppBar
            AppBarUi.AppBar({}, onNavigateToLogin)
        }

        item {
            // 배너
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            ) {
                AutoScrollingBanner(
                    imageResIds = listOf(
                        R.drawable.ic_banner1,
                        R.drawable.ic_banner2,
                        R.drawable.ic_banner1,
                        R.drawable.ic_banner2,
                        R.drawable.ic_banner1,
                        R.drawable.ic_banner2
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            ReviewHome(
                text = stringResource(id = R.string.txt_home_review_title_test1),
                imageResIds = listOf(
                    R.drawable.ic_cake_review1,
                    R.drawable.ic_cake_review2,
                    R.drawable.ic_cake_review3,
                    R.drawable.ic_cake_review4,
                    R.drawable.ic_cake_review5,
                    R.drawable.ic_add_review
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            ReviewHome(
                text = stringResource(id = R.string.txt_home_review_title_test2),
                imageResIds = listOf(
                    R.drawable.ic_bingsu_review1,
                    R.drawable.ic_bingsu_review2,
                    R.drawable.ic_bingsu_review3,
                    R.drawable.ic_bingsu_review1,
                    R.drawable.ic_bingsu_review2,
                    R.drawable.ic_add_review
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            ReviewHome(
                text = stringResource(id = R.string.txt_home_review_title_test3),
                imageResIds = listOf(
                    R.drawable.ic_bingsu_review1,
                    R.drawable.ic_bingsu_review2,
                    R.drawable.ic_bingsu_review3,
                    R.drawable.ic_bingsu_review1,
                    R.drawable.ic_bingsu_review2,
                    R.drawable.ic_add_review
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
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
        PagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
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
    val color = if (isActive) MainColor else CornflowerBlue

    Box(
        modifier = Modifier
            .size(8.dp)
            .background(color, shape = CircleShape)
            .padding(4.dp)
    )
}

// 후기 이미지
@Composable
fun ReviewHome(
    text: String,
    imageResIds: List<Int>,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = DessertTimeTheme.typography.textStyleBold18,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp)
    )
    Spacer(modifier = Modifier.height(12.dp))
    // 사용자가 수평으로 스크롤할 수 있도록 LazyRow 사용
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp) // 이미지 간의 간격 설정
    ) {
        items(imageResIds.size) { index ->
            Image(
                painter = painterResource(id = imageResIds[index]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(106.dp) // 이미지 너비 설정
                    .height(140.dp) // 이미지 높이 설정
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen({})
}
