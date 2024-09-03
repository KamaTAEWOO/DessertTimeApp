package com.desserttime.auth.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import com.desserttime.auth.AuthViewModel
import com.desserttime.design.R
import com.desserttime.design.theme.AthensGray
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.DoveGray
import com.desserttime.design.theme.Flamingo
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.Silver
import com.desserttime.design.ui.common.CommonUi
import timber.log.Timber

private const val TAG = "SignUpChooseScreen"

@Composable
fun SignUpChooseScreen(
    onNavigateToSignUpComplete: () -> Unit,
    onBack: () -> Unit,
    authViewModel: AuthViewModel
) {
    val selectedItems = remember { mutableStateListOf<Int>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 26.dp, end = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.padding(top = 66.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp) // Adjust height to ensure visibility
                .border(
                    1.dp,
                    AthensGray,
                    RoundedCornerShape(10.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Flamingo,
                        RoundedCornerShape(10.dp)
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f) // Width is 50% of the parent
                    .fillMaxHeight() // Full height
                    .background(
                        Flamingo,
                        RoundedCornerShape(10.dp)
                    ) // Use Color.Red if Flamingo is not defined
            )
        }
        Spacer(Modifier.padding(top = 28.dp))
        Text(
            text = stringResource(id = R.string.txt_choose_interest),
            style = DessertTimeTheme.typography.textStyleBold26,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(Modifier.padding(top = 6.dp))
        Text(
            text = stringResource(id = R.string.txt_choose_interest_hint),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = DoveGray,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
    // 취향 리스트
    Column {
        Spacer(Modifier.padding(top = 184.dp))
        ViewSelectTasteData(selectedItems)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 58.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        CommonUi.NextButton(
            text = stringResource(R.string.txt_next),
            onClick = {
                saveSignUpChooseData(
                    onNavigateToSignUpComplete,
                    authViewModel,
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
private fun ViewSelectTasteData(selectedItems: SnapshotStateList<Int>) {
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
    selectedItems: SnapshotStateList<Int>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp)
    ) {
        items(items.size) { index ->
            val item = items[index]

            val isSelected = selectedItems.contains(index)
            GridItem(
                imageRes = item.first,
                text = item.second,
                isSelected = isSelected,
                onClick = {
                    if (isSelected) {
                        // 선택된 아이템 해제
                        selectedItems.remove(index)
                    } else {
                        // 최대 5개까지만 추가 가능
                        if (selectedItems.size < 5) {
                            selectedItems.add(index)
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

private fun saveSignUpChooseData(
    onNavigateToSignUpComplete: () -> Unit,
    authViewModel: AuthViewModel,
    selectedItems: SnapshotStateList<Int>
) {
    // 갯수 체크 후 데이터 저장 -> 데이터가 없을 경우 0으로 저장
    // selectedItems 배열의 크기를 체크한 후, 데이터 저장
    authViewModel.saveMemberPickCategory1Data(if (selectedItems.size > 0) selectedItems[0] + 1 else 0)
    authViewModel.saveMemberPickCategory2Data(if (selectedItems.size > 1) selectedItems[1] + 1 else 0)
    authViewModel.saveMemberPickCategory3Data(if (selectedItems.size > 2) selectedItems[2] + 1 else 0)
    authViewModel.saveMemberPickCategory4Data(if (selectedItems.size > 3) selectedItems[3] + 1 else 0)
    authViewModel.saveMemberPickCategory5Data(if (selectedItems.size > 4) selectedItems[4] + 1 else 0)

    authViewModel.printAllData()
    // 서버로 데이터 보내기
    authViewModel.requestUserSignUp()
    onNavigateToSignUpComplete()
}
