package com.desserttime.category

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.desserttime.design.R
import com.desserttime.design.theme.Alto
import com.desserttime.design.theme.Black5
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.TundoraCategory
import com.desserttime.design.ui.common.AppBarUi
import timber.log.Timber

private val TAG: String = "CategoryScreen::"

@Composable
fun CategoryScreen(
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    val categoryUiState by categoryViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(categoryViewModel) {
        categoryViewModel.requestCategoryData()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 20.dp, end = 20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        AppBarUi.AppBar(stringResource(id = R.string.txt_bottom_category))
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.txt_category_sub_title),
            style = DessertTimeTheme.typography.textStyleBold18,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn에 Divider를 추가하기 전에, Divider를 LazyColumn 외부에 위치시킵니다.
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            items(categoryUiState.allCategory) { category ->
                CategoryItem(category.dessertCategoryId, category.dessertName)
            }
        }

        // LazyColumn 외부에 Divider를 위치시킵니다.
        Divider(
            color = Alto,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CategoryItem(
    categoryMainId: Int,
    categoryMainName: String
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Divider(
            color = Alto,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp, start = 12.dp, end = 12.dp)
                .clickable { isExpanded = !isExpanded } // Row 클릭 시 확장 상태 변경
        ) {
            CategoryItemImage(categoryMainId)
            Spacer(modifier = Modifier.width(12.dp))
            CategoryItemText(categoryMainName)
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { isExpanded = !isExpanded } // 버튼 클릭 시 확장 상태 변경
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_expand),
                    contentDescription = stringResource(id = R.string.img_category_description),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        if (isExpanded) {
            Divider(
                color = Alto,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
            // 확장 상태일 때 추가 정보 표시
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Black5)
            ) {
                Text(
                    text = "추가 정보: $categoryMainName",
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    color = TundoraCategory
                )
                // 여기에 추가로 표시하고 싶은 내용을 추가할 수 있습니다.
            }
        }
    }
}

@Composable
fun CategoryItemImage(
    categoryMainId: Int
) {
    Timber.i("$TAG categoryMainId: $categoryMainId")

    val imageResIds = listOf(
        R.drawable.ic_off_1,
        R.drawable.ic_off_2,
        R.drawable.ic_off_3,
        R.drawable.ic_off_4,
        R.drawable.ic_off_5,
        R.drawable.ic_off_6,
        R.drawable.ic_off_7,
        R.drawable.ic_off_8,
        R.drawable.ic_off_9,
        R.drawable.ic_off_10,
        R.drawable.ic_off_11,
        R.drawable.ic_off_12,
        R.drawable.ic_off_13,
        R.drawable.ic_off_14,
        R.drawable.ic_off_15,
        R.drawable.ic_off_16,
        R.drawable.ic_off_17,
        R.drawable.ic_off_18,
        R.drawable.ic_off_19,
        R.drawable.ic_off_20,
    )

    // 인덱스 범위 체크 추가
    val imageResId = if (categoryMainId in imageResIds.indices) {
        imageResIds[categoryMainId]
    } else {
        R.drawable.ic_off_1 // 기본 이미지
    }

    Image(
        painter = painterResource(id = imageResId),
        contentDescription = stringResource(id = R.string.img_category_description),
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .width(24.dp) // 이미지 너비 설정
            .height(24.dp), // 이미지 높이 설정
        colorFilter = ColorFilter.tint(MainColor)
    )
}

@Composable
fun CategoryItemText(
    categoryMainName: String
) {
    Text(
        text = categoryMainName,
        style = DessertTimeTheme.typography.textStyleRegular16,
        color = TundoraCategory,
    )
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    // Sample data for preview
    CategoryScreen()
}
