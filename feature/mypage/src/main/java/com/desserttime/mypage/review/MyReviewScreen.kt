package com.desserttime.mypage.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import com.desserttime.design.R
import com.desserttime.design.theme.Black50
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.Gallery
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.Mercury
import com.desserttime.design.theme.Salem
import com.desserttime.design.theme.Salem20
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.design.ui.common.CommonPopup
import timber.log.Timber

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
                MyReviewContent()
            }
        }
    )
}

@Composable
fun MyReviewContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Header text: wheat 사용 내역
        Text(
            text = "24년 06월",
            color = Black50,
            style = DessertTimeTheme.typography.textStyleMedium12,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 20.dp, top = 20.dp, bottom = 12.dp)
        )

        // Scrollable list of wheat detail items
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(5) {
                MyReviewItem(it)
                Spacer(modifier = Modifier.height(4.dp)) // Adds space between items
            }
        }
    }
}

// Item
@Composable
fun MyReviewItem(index: Int) {
    var showDialog by remember { mutableStateOf(false) }
    val itemAlpha = if (index == 2) 0.4f else 1f
    val buttonOffset by remember { mutableStateOf(Offset.Zero) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
            .background(Color.White.copy(alpha = itemAlpha))
    ) {
        Divider(color = Mercury, thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(108.dp)
                .background(Color.White.copy(alpha = itemAlpha))
        ) {
            // Review Image
            Image(
                painter = painterResource(id = R.drawable.ic_cake_review1),
                contentDescription = stringResource(id = R.string.txt_my_review_title_image),
                modifier = Modifier
                    .size(108.dp, 92.dp)
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                    .background(Color.Red.copy(alpha = itemAlpha)),
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
                    color = Color.Black.copy(alpha = itemAlpha)
                )
                Text(
                    text = "바질치즈스콘",
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    color = Color.Black.copy(alpha = itemAlpha),
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
                        contentDescription = stringResource(id = R.string.txt_review_delete),
                        tint = Color.Unspecified.copy(alpha = itemAlpha)
                    )
                }

                // 팝업이 표시될 때
                if (showDialog) {
                    ReviewItemDeletePopup(
                        anchorPosition = buttonOffset,
                        onDeleteConfirm = {
                            // 삭제 확인 로직을 여기에 추가
                            showDialog = false // 팝업 닫기
                        },
                        onDismiss = {
                            showDialog = false // 팝업 닫기
                        }
                    )
                }
            }
        }

        Divider(color = Mercury, thickness = 1.dp)
    }
}

@Composable
fun ReviewItemDeletePopup(
    anchorPosition: Offset, // 버튼의 위치
    onDeleteConfirm: () -> Unit, // 삭제 확인 시 호출되는 콜백 함수
    onDismiss: () -> Unit // 팝업 닫기 시 호출되는 콜백 함수
) {
    // 팝업창의 표시 여부를 위한 상태
    var showDialog by remember { mutableStateOf(false) }

    Timber.i("anchorPosition: $anchorPosition, x: ${anchorPosition.x}, y: ${anchorPosition.y}")

    // 팝업 트리거 버튼
    Popup(
        alignment = Alignment.TopStart,
        offset = IntOffset(
            x = -140, // 팝업창을 버튼에 맞추기 위한 offset
            y = 230 // 항목의 위치에 따라 조정
        ),
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = Color.White,
            modifier = Modifier
                .clip(RoundedCornerShape(4.dp))
                .border(1.dp, Gallery, RoundedCornerShape(4.dp))
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(onClick = { showDialog = true }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = stringResource(id = R.string.txt_review_delete),
                            tint = MainColor,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(id = R.string.txt_review_delete), // "삭제하기"
                            color = MainColor,
                            style = DessertTimeTheme.typography.textStyleRegular12
                        )
                    }
                }
            }
        }
    }

    // showDialog가 true일 때 화면 중앙에 CommonPopup을 Dialog로 표시
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            CommonPopup(
                title = stringResource(R.string.txt_review_delete_popup_title),
                onConfirm = {
                    showDialog = false
                    onDeleteConfirm() // 삭제 확인 콜백 호출
                },
                onDismiss = {
                    showDialog = false
                    onDismiss() // 팝업 닫기 콜백 호출
                },
                onPopupDismiss = {
                    showDialog = false
                }
            )
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
