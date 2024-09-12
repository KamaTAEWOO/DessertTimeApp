package com.desserttime.category

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.desserttime.design.R
import com.desserttime.design.theme.Alto
import com.desserttime.design.theme.Black60
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.DustyGray
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.Mercury
import com.desserttime.design.theme.Pippin
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.domain.model.LikeData
import timber.log.Timber

private const val TAG = "SubCategoryReviewScreen::"

@Composable
fun SubCategoryReviewScreen(
    categoryViewModel: CategoryViewModel,
    onNavigateToCategory: () -> Unit
) {
    val categoryUiState = categoryViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            AppBarUi.AppBar(
                { onNavigateToCategory() },
                categoryUiState.value.subCategory,
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    // Header Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween // Space between header items
                    ) {
                        Text(
                            text = stringResource(id = R.string.txt_like_review),
                            style = DessertTimeTheme.typography.textStyleBold18,
                            color = Color.Black
                        )
                        // SortDropDown() // Dropdown for sorting
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Like List
                    LikeList()
                }
            }
        }
    )
}

// @OptIn(ExperimentalMaterial3Api::class)
// @Composable
// fun SortDropDown() {
//    var expanded by remember { mutableStateOf(false) }
//    var selectedOption by remember { mutableStateOf("최신순") }
//    val interactionSource = remember { MutableInteractionSource() } // Required interactionSource
//
//    val options = listOf("최신순", "좋아요 순", "오랜된 순")
//
//    ExposedDropdownMenuBox(
//        expanded = expanded,
//        onExpandedChange = { expanded = !expanded },
//        modifier = Modifier
//            .wrapContentSize()
//            .background(Color.Transparent)
//    ) {
//        TextField(
//            value = selectedOption,
//            onValueChange = { },
//            readOnly = true,
//            trailingIcon = {
//                Icon(
//                    imageVector = Icons.Default.ArrowDropDown,
//                    contentDescription = null
//                )
//            },
//            interactionSource = interactionSource, // Apply interactionSource
//            modifier = Modifier
//                .background(Color.Transparent), // Ensure TextField background is transparent
//            colors = TextFieldDefaults.textFieldColors(
//                containerColor = Color.Transparent, // Transparent container
//                focusedIndicatorColor = Color.Transparent, // No underline when focused
//                unfocusedIndicatorColor = Color.Transparent // No underline when not focused
//            )
//        )
//        ExposedDropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            modifier = Modifier
//                .background(Color.White) // Set background of dropdown menu
//                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)) // Optional: border for better visibility
//        ) {
//            options.forEach { option ->
//                DropdownMenuItem(onClick = {
//                    selectedOption = option
//                    expanded = false
//                }, text = {
//                    Text(
//                        text = option,
//                        style = DessertTimeTheme.typography.textStyleMedium12,
//                        color = ShipGray
//                    )
//                }
//                )
//            }
//        }
//    }
// }

@Composable
fun LikeList() {
    val likeData = likeItemData()

    LazyColumn {
        items(10) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(WildSand)
                    .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                LikeItem(
                    likeData.icLikeProfile,
                    stringResource(id = likeData.nickName),
                    stringResource(id = likeData.date),
                    likeData.likeCount,
                    likeData.title,
                    likeData.score,
                    likeData.likePicture,
                    stringResource(id = likeData.content),
                    likeData.materialArr
                )
            }
        }
    }
}

fun likeItemData(): LikeData = LikeData(
    icLikeProfile = R.drawable.ic_like_profile,
    nickName = R.string.txt_like_nickname,
    date = R.string.txt_like_date,
    likeCount = R.string.txt_like_count,
    title = R.string.txt_like_title,
    score = 4,
    likePicture = R.drawable.ic_like_picture,
    content = R.string.txt_like_content,
    materialArr = listOf(
        R.string.txt_review_write_material_selection_2,
        R.string.txt_review_write_material_selection_6,
        R.string.txt_review_write_material_selection_2,
        R.string.txt_review_write_material_selection_6,
        R.string.txt_review_write_material_selection_2
    )
)

@Composable
fun LikeItem(
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
            .border(1.dp, Mercury, shape = RoundedCornerShape(10.dp))
            .clickable { }
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
                .padding(start = 24.dp, end = 20.dp, bottom = 24.dp)
        ) {
            // title
            Text(
                text = stringResource(id = title),
                style = DessertTimeTheme.typography.textStyleBold16,
                color = Color.Black
            )
            ScoreCheck(score)
            MenuPicture(likePicture)
            Text(
                text = content,
                style = DessertTimeTheme.typography.textStyleRegular14,
                color = Black60,
                maxLines = 1, // Restricting to a single line
                overflow = TextOverflow.Ellipsis, // Adding ellipsis if text exceeds the max line
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )
            Spacer(modifier = Modifier.padding(top = 16.dp))
            MaterialItemList(materialArr)
        }
    }
}

@Composable
fun ScoreCheck(score: Int) {
    val imageResource = painterResource(id = R.drawable.ic_star_off)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        repeat(4) {
            Image(
                painter = imageResource,
                contentDescription = stringResource(id = R.string.img_review_write_score_description),
                modifier = Modifier.padding(end = 4.dp)
            )
        }
    }
}

@Composable
fun MenuPicture(likePicture: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .size(79.dp)
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

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MaterialItemList(items: List<Int>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 3.dp)
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            FlowRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEach { material ->
                    MaterialItemRound(
                        categorySubName = stringResource(id = material),
                        modifier = Modifier
                            .padding(end = 3.dp, bottom = 8.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Pippin)
                    )
                }
            }
        }
    }
}

@Composable
fun MaterialItemRound(
    categorySubName: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = categorySubName,
            style = DessertTimeTheme.typography.textStyleMedium12,
            color = MainColor,
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SubCategoryReviewScreenPreview() {
    // SubCategoryReviewScreen({})
}
