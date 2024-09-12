package com.desserttime.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.desserttime.core.navigation.destination.MainDestination
import com.desserttime.design.R
import com.desserttime.design.theme.Alabaster
import com.desserttime.design.theme.Alto
import com.desserttime.design.theme.AltoAgree
import com.desserttime.design.theme.AzureRadiance
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.Black60
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.DustyGray
import com.desserttime.design.theme.Gallery
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.Tundora50
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.design.ui.common.CommonUi
import androidx.compose.foundation.layout.Row as Ro

private const val TAG = "LikeDetailScreen::"

@Composable
fun SubCategoryReviewDetailScreen(
    categoryViewModel: CategoryViewModel,
    onNavigateToSubCategoryReview: () -> Unit
) {
    val subCategoryReviewData = subCategoryReviewItemData()

    Scaffold(
        modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            AppBarUi.AppBar(
                { onNavigateToSubCategoryReview() },
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
                SubCategoryReviewDetailItem(
                    subCategoryReviewData.icLikeProfile,
                    stringResource(id = subCategoryReviewData.nickName),
                    stringResource(id = subCategoryReviewData.date),
                    subCategoryReviewData.likeCount,
                    subCategoryReviewData.title,
                    subCategoryReviewData.score,
                    subCategoryReviewData.likePicture,
                    stringResource(id = subCategoryReviewData.content),
                    subCategoryReviewData.materialArr
                )
            }
        }
    )
}

@Composable
fun SubCategoryReviewDetailItem(
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
        Ro(
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
            AccusationButton()
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

@Composable
fun AccusationButton() {
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.txt_like_report),
            style = DessertTimeTheme.typography.textStyleRegular12,
            color = Tundora50,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .wrapContentWidth()
                .clickable {
                    showDialog = true // 다이얼로그 표시
                }
        )
    }

    if (showDialog) {
        AccusationDialog(
            onDismiss = { showDialog = false },
            onConfirm = { selectedItems, contentText ->
                showDialog = false
            },
            initialSelectedItems = listOf("저작권 침해")
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccusationDialog(
    onDismiss: () -> Unit,
    onConfirm: (List<String>, String) -> Unit,
    initialSelectedItems: List<String> = emptyList()
) {
    // selectedItems를 명확하게 String 타입 리스트로 선언
    var selectedItems by remember { mutableStateOf(initialSelectedItems) }
    var contentText by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(4.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.txt_accusation_title),
                    style = DessertTimeTheme.typography.textStyleRegular18,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(17.dp))

                CommonUi.Divide(Gallery)

                Spacer(modifier = Modifier.height(20.dp))

//                val groupedAccusations = likeUiState.allAccusations.values.chunked(2)
//
//                groupedAccusations.forEach { rowOptions ->
//                    Ro(
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        rowOptions.forEach { option ->
//                            Ro(
//                                modifier = Modifier
//                                    .weight(1f)
//                                    .clickable {
//                                        // 모든 항목 선택 해제
//                                        selectedItems = listOf(option)
//                                    }
//                                    .padding(8.dp)
//                            ) {
//                                CustomRadioButton(
//                                    selected = selectedItems.contains(option)
//                                ) { isChecked ->
//                                    // 체크박스 상태에 따라 항목 선택
//                                    selectedItems = if (isChecked as Boolean) {
//                                        listOf(option) // 체크된 항목만 선택
//                                    } else {
//                                        listOf() // 체크 해제 시 선택 항목 비우기
//                                    }
//                                }
//                                Text(
//                                    text = option,
//                                    style = DessertTimeTheme.typography.textStyleRegular14,
//                                    color = DustyGray,
//                                    modifier = Modifier.padding(start = 8.dp)
//                                )
//                            }
//                        }
//                    }
//                }

                Spacer(modifier = Modifier.height(22.dp))

                OutlinedTextField(
                    value = contentText,
                    onValueChange = { newText -> contentText = newText },
                    textStyle = DessertTimeTheme.typography.textStyleRegular16,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(138.dp),
                    placeholder = {
                        Text(
                            text = stringResource(id = R.string.txt_inquiry_content_hint),
                            style = DessertTimeTheme.typography.textStyleRegular12,
                            color = Black30
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = WildSand,
                        focusedIndicatorColor = AzureRadiance,
                        unfocusedIndicatorColor = WildSand
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .background(if (selectedItems.isNotEmpty()) MainColor else AltoAgree, RoundedCornerShape(8.dp)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.btn_accusation),
                        style = DessertTimeTheme.typography.textStyleMedium18,
                        color = if (selectedItems.isNotEmpty()) Color.White else DustyGray,
                        modifier = Modifier.clickable { onConfirm(selectedItems, contentText) }
                    )
                }
            }
        }
    }
}

@Composable
fun CustomRadioButton(
    selected: Boolean,
    onClick: (Any?) -> Unit
) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(if (selected) MainColor else Color.Transparent)
            .border(
                width = 2.dp,
                color = if (selected) MainColor else WildSand,
                shape = CircleShape
            )
            .clickable(onClick = { onClick(!selected) }),
        contentAlignment = Alignment.Center
    ) {
        if (selected) {
            // 선택된 상태에서는 이미지 표시
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                tint = Color.White, // 이미지 색상
                modifier = Modifier.size(12.dp)
            )
        }
    }
}
