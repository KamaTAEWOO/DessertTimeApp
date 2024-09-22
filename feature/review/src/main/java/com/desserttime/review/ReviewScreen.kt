package com.desserttime.review

import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.desserttime.design.R
import com.desserttime.design.theme.Black60
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.Gallery
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.TundoraCategory
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import timber.log.Timber

private const val TAG = "ReviewScreen::"

@Composable
fun ReviewScreen(
    reviewViewModel: ReviewViewModel,
    onNavigateToReviewWrite: () -> Unit
) {
    // 카메라 실행을 위한 launcher
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // 카메라에서 이미지 캡처 후 결과 처리
        val bitmap = result.data?.extras?.get("data") as? Bitmap
        if (bitmap != null) {
            Timber.i("$TAG imageBitmap: $bitmap")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AppBarUi.AppBar(
            stringResource(id = R.string.txt_bottom_review),
            {},
            {}
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .background(WildSand)
        )
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
            Box(
                modifier = Modifier
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
                    .padding(horizontal = 12.dp),
                onNavigateToReviewWrite = onNavigateToReviewWrite,
                reviewViewModel = reviewViewModel
            )
            // Floating button positioned at the bottom
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .zIndex(1f)
            ) {
                Button(
                    onClick = {
                        // 카메라 앱 호출을 위한 Intent 생성
                        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        // 카메라 인텐트 실행
                        cameraLauncher.launch(cameraIntent)
                    },
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
fun ReviewItemView(
    modifier: Modifier = Modifier,
    onNavigateToReviewWrite: () -> Unit,
    reviewViewModel: ReviewViewModel
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(10) {
            ReviewItem(
                reviewViewModel,
                onNavigateToReviewWrite
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(WildSand)
            )
        }
    }
}

@Composable
fun ReviewItem(
    reviewViewModel: ReviewViewModel,
    onNavigateToReviewWrite: () -> Unit
) {
    val reviewUiState by reviewViewModel.uiState.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf(false) }
    var buttonOffset by remember { mutableStateOf(Offset.Zero) }
    val storeName by remember { mutableStateOf("스타벅스") }
    val storeMenu by remember { mutableStateOf("아메리카노") }

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
                text = storeName,
                style = DessertTimeTheme.typography.textStyleRegular16,
                color = Black60
            )
            // Text
            Text(
                text = storeMenu,
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
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .onGloballyPositioned { coordinates ->
                        buttonOffset = coordinates.positionInWindow()
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_review_write),
                    contentDescription = stringResource(id = R.string.img_review_write_description),
                    tint = Color.Gray,
                    modifier = Modifier.clickable {
                        sendReviewData(
                            reviewUiState,
                            storeName,
                            storeMenu,
                            onNavigateToReviewWrite
                        )
                    }
                )
                // Image -> 클릭 시 삭제하기 팝업창 Image 위에나 아래에 떠야함.
                IconButton(
                    onClick = { showDialog = true }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_review_delete),
                        contentDescription = stringResource(id = R.string.txt_review_delete)
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
    }
}

@Composable
fun ReviewItemDeletePopup(
    anchorPosition: Offset, // Position of the button
    onDeleteConfirm: () -> Unit, // 삭제 확인 시 호출되는 콜백 함수
    onDismiss: () -> Unit // 팝업 닫기 시 호출되는 콜백 함수
) {
    Timber.i("anchorPosition: $anchorPosition, x: ${anchorPosition.x}, y: ${anchorPosition.y}")

    Popup(
        alignment = Alignment.TopStart,
        offset = IntOffset(
            x = -32, // Align the popup with the button
            y = 120 // Adjust as needed based on the item's position
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
                TextButton(onClick = { onDeleteConfirm() }) {
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
}

// 리뷰 버튼 눌렀을 때 호출되는 함수
fun sendReviewData(
    reviewUiState: ReviewState,
    storeName: String,
    storeMenu: String,
    onNavigateToReviewWrite: () -> Unit
) {
    reviewUiState.storeName = storeName
    reviewUiState.storeMenu = storeMenu
    onNavigateToReviewWrite()
}

@Preview(showBackground = true)
@Composable
fun ReviewScreenPreview() {}
