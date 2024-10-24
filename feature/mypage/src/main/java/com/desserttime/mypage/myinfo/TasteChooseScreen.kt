package com.desserttime.mypage.myinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.desserttime.design.R
import com.desserttime.design.theme.AthensGray
import com.desserttime.design.theme.Black60
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.MineShaft
import com.desserttime.design.theme.Silver
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.design.ui.common.CommonUi
import com.desserttime.mypage.MyPageState
import com.desserttime.mypage.MyPageViewModel
import timber.log.Timber

private const val TAG = "TasteChooseScreen::"

@Composable
fun TasteChooseScreen(
    onNavigateToMyInfo: () -> Unit,
    onBack: () -> Unit,
    myPageViewModel: MyPageViewModel
) {
    val selectedItems = remember { mutableStateListOf<String>() }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = {
            AppBarUi.AppBar(
                onBackClick = { onBack() },
                title = stringResource(id = R.string.txt_my_info_taste_title)
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
                    .background(Color.White)
            ) {
                // content
                TasteChooseContent(
                    myPageViewModel = myPageViewModel,
                    onNavigateToMyInfo = onNavigateToMyInfo,
                    selectedItems = selectedItems
                )
            }
        }
    )
}

@Composable
fun TasteChooseContent(
    myPageViewModel: MyPageViewModel,
    onNavigateToMyInfo: () -> Unit,
    selectedItems: SnapshotStateList<String>
) {
    // ViewModel에 memberData?.memo에 저장
    val myPageUiState by myPageViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(start = 26.dp, end = 26.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.txt_my_info_taste_add_choose),
            style = DessertTimeTheme.typography.textStyleBold26,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = stringResource(id = R.string.txt_my_info_taste_description),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = Black60
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            text = stringResource(id = R.string.txt_my_info_taste),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = MineShaft
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
    // 취향 리스트
    Column {
        ViewSelectTasteData(selectedItems)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        CommonUi.NextButton(
            text = stringResource(R.string.txt_choose_complete),
            onClick = {
                saveMyInputChooseData(
                    myPageUiState,
                    onNavigateToMyInfo,
                    selectedItems
                )
            },
            background = MainColor,
            textColor = Color.White,
            enabled = true
        )
    }
}

@Composable
private fun ViewSelectTasteData(selectedItems: SnapshotStateList<String>) {
    val items = listOf(
        R.drawable.ic_fish_shaped_bun_off to stringResource(id = R.string.txt_fish_shaped_bun),
        R.drawable.ic_baked_confectionery_off to stringResource(id = R.string.txt_baked_confectionery),
        R.drawable.ic_donut_off to stringResource(id = R.string.txt_donut),
        R.drawable.ic_rice_cake_off to stringResource(id = R.string.txt_rice_cake),
        R.drawable.ic_macaron_off to stringResource(id = R.string.txt_macaron),
        R.drawable.ic_bakery_off to stringResource(id = R.string.txt_bakery),
        R.drawable.ic_fruit_dessert_off to stringResource(id = R.string.txt_fruit_dessert),
        R.drawable.ic_summer_off to stringResource(id = R.string.txt_summer_dessert),
        R.drawable.ic_yogert_off to stringResource(id = R.string.txt_yogurt),
        R.drawable.ic_tradition_dessert_off to stringResource(id = R.string.txt_tradition_dessert),
        R.drawable.ic_chocolate_off to stringResource(id = R.string.txt_chocolate),
        R.drawable.ic_cake_off to stringResource(id = R.string.txt_cake),
        R.drawable.ic_cookie_off to stringResource(id = R.string.txt_cookie),
        R.drawable.ic_tart_off to stringResource(id = R.string.txt_tart),
        R.drawable.ic_pan_cake_off to stringResource(id = R.string.txt_pan_cake),
        R.drawable.ic_pastry_off to stringResource(id = R.string.txt_pastries),
        R.drawable.ic_pudding_off to stringResource(id = R.string.txt_pudding)
    )

    SelectTasteRecyclerView(items = items, selectedItems = selectedItems)
}

@Composable
fun SelectTasteRecyclerView(
    items: List<Pair<Int, String>>,
    selectedItems: SnapshotStateList<String>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp)
    ) {
        items(items.size) { index ->
            val item = items[index]
            val isSelected = selectedItems.contains(item.second) // Check using String

            GridItem(
                imageRes = item.first,
                text = item.second,
                isSelected = isSelected,
                onClick = {
                    if (isSelected) {
                        // 선택된 아이템 해제
                        selectedItems.remove(item.second) // Remove by String
                    } else {
                        // 최대 5개까지만 추가 가능
                        if (selectedItems.size < 5) {
                            selectedItems.add(item.second) // Add String
                        } else {
                            Timber.i("$TAG 최대 5개까지만 선택 가능합니다.")
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun GridItem(imageRes: Int, text: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(start = 5.dp, end = 5.dp, top = 10.dp)
            .size(80.dp)
            .clip(CircleShape) // Clip to a circular shape
            .border(
                width = 1.dp,
                color = if (isSelected) MainColor else AthensGray,
                shape = CircleShape
            )
            .clickable { onClick() } // 클릭 가능하도록 설정
            .padding(8.dp)
    ) {
        Icon(
            painter = painterResource(id = imageRes),
            contentDescription = text,
            modifier = Modifier.size(32.dp),
            tint = if (isSelected) MainColor else Silver
        )
        Text(
            text = text,
            style = DessertTimeTheme.typography.textStyleRegular12,
            color = if (isSelected) MainColor else Silver, // 선택 시 텍스트 색상 변경
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

private fun saveMyInputChooseData(
    myPageUiState: MyPageState,
    onNavigateToMyInfo: () -> Unit,
    selectedItems: SnapshotStateList<String>
) {
    Timber.i("$TAG 선택된 아이템: $selectedItems")

    // Join selectedItems into a comma-separated string
    val selectedItemsString = selectedItems.joinToString(", ") { it.toString() }

    myPageUiState.taste = selectedItemsString

    onNavigateToMyInfo()
}
