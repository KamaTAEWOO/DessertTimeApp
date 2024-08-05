package com.desserttime.category

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
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

private const val TAG: String = "CategoryScreen::"

@Composable
fun CategoryScreen(
    categoryViewModel: CategoryViewModel = hiltViewModel()
) {
    val categoryUiState by categoryViewModel.uiState.collectAsStateWithLifecycle()
    var expandedItemId by remember { mutableStateOf<Int?>(null) }

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
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            items(categoryUiState.allCategory) { category ->
                CategoryMainItem(
                    categoryUiState,
                    category.dessertCategoryId,
                    category.dessertName,
                    isExpanded = expandedItemId == category.dessertCategoryId,
                    onExpandToggle = {
                        expandedItemId = if (expandedItemId == category.dessertCategoryId) null else category.dessertCategoryId
                    }
                )
            }
        }
    }
}

@Composable
fun CategoryMainItem(
    categoryUiState: CategoryState,
    categoryMainId: Int,
    categoryMainName: String,
    isExpanded: Boolean,
    onExpandToggle: () -> Unit
) {
    Divider(
        color = Alto,
        thickness = 1.dp,
        modifier = Modifier.fillMaxWidth()
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp)
                .clickable { onExpandToggle() }
                .testTag("$categoryMainId")
        ) {
            val color = if (isExpanded) MainColor else TundoraCategory
            CategoryMainItemImage(
                categoryMainId,
                color
            )
            Spacer(modifier = Modifier.width(12.dp))
            CategoryMainItemText(
                categoryMainName,
                if (isExpanded) Color.Black else TundoraCategory
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { onExpandToggle() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_expand),
                    contentDescription = stringResource(id = R.string.img_category_description),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(16.dp),
                    colorFilter = ColorFilter.tint(color)
                )
            }
        }

        if (isExpanded) {
            Timber.i("$TAG categoryMainId: $categoryMainId")
            CategorySubItem(categoryUiState, categoryMainId)
        }
    }
}

@Composable
fun CategoryMainItemImage(
    categoryMainId: Int,
    color: Color
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
        R.drawable.ic_off_20
    )

    val imageResId = imageResIds.getOrElse(categoryMainId) {
        R.drawable.ic_off_1 // Fallback image
    }

    Image(
        painter = painterResource(id = imageResId),
        contentDescription = stringResource(id = R.string.img_category_description),
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(24.dp),
        colorFilter = ColorFilter.tint(color)
    )
}

@Composable
fun CategoryMainItemText(
    categoryMainName: String,
    color: Color
) {
    Text(
        text = categoryMainName,
        style = DessertTimeTheme.typography.textStyleRegular16,
        color = color
    )
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategorySubItem(
    categoryUiState: CategoryState,
    categoryMainId: Int
) {
    Divider(
        color = Alto,
        thickness = 1.dp,
        modifier = Modifier.fillMaxWidth()
    )
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .background(Black5)
            .padding(vertical = 4.dp)
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            categoryUiState.allCategory.forEach { rowItems ->
                rowItems.secondCategory?.forEach { item ->
                    if (item.parentDCId == categoryMainId) {
                        CategorySubItemRound(item.dessertName)
                    }
                }
            }
        }
    }
    Divider(
        color = Alto,
        thickness = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RectangleShape,
                clip = false
            )
    )
}

@Composable
fun CategorySubItemRound(
    categorySubName: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(50))
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = categorySubName,
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = TundoraCategory
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryScreenPreview() {
    CategoryScreen()
}
