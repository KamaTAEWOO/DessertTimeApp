package com.desserttime.review

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.Alto
import com.desserttime.design.theme.AzureRadiance
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.DoveGray
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi

@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ReviewWriteScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        var inputStoreName by remember { mutableStateOf("") }
        var inputMenuName by remember { mutableStateOf("") }
        var inputCategoryName by remember { mutableStateOf("") }
        var inputMaterialName by remember { mutableStateOf("") }
        var inputScore by remember { mutableStateOf("") }
        var inputReviewBehind by remember { mutableStateOf("") }
        val materialArr = listOf(
            stringResource(id = R.string.txt_review_write_material_selection_1),
            stringResource(id = R.string.txt_review_write_material_selection_2),
            stringResource(id = R.string.txt_review_write_material_selection_3),
            stringResource(id = R.string.txt_review_write_material_selection_4),
            stringResource(id = R.string.txt_review_write_material_selection_5),
            stringResource(id = R.string.txt_review_write_material_selection_6),
            stringResource(id = R.string.txt_review_write_material_selection_7)
        )

        AppBarUi.AppBar(
            {},
            stringResource(id = R.string.txt_bottom_review),
            {},
            {}
        )
        Box(modifier = Modifier.padding(top = 20.dp))
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            EditTextBox(
                title = stringResource(id = R.string.txt_review_write_store_name),
                content = inputStoreName,
                contentHint = "",
                onContentChange = { newText -> inputStoreName = newText }
            )
        }
        Box(modifier = Modifier.padding(top = 20.dp))
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            EditTextBox(
                title = stringResource(id = R.string.txt_review_write_menu_name),
                content = inputMenuName,
                contentHint = "",
                onContentChange = { newText -> inputStoreName = newText }
            )
        }
        Box(modifier = Modifier.padding(top = 20.dp))
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            EditTextBox(
                title = stringResource(id = R.string.txt_review_write_category),
                content = inputCategoryName,
                contentHint = "",
                onContentChange = { newText -> inputStoreName = newText }
            )
        }
        // 재료 선택
        Box(modifier = Modifier.padding(top = 20.dp))
        Text(
            text = stringResource(id = R.string.txt_review_write_material_selection),
            style = DessertTimeTheme.typography.textStyleBold14,
            color = DoveGray,
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 16.dp)
        )
        Box(modifier = Modifier.padding(top = 8.dp))
        MaterialItemList(materialArr)
        // 점수
        // 후기 작성
        // 메뉴 사진
        // 작성완료
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextBox(
    title: String,
    content: String,
    contentHint: String,
    onContentChange: (String) -> Unit
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = DessertTimeTheme.typography.textStyleBold14,
            color = DoveGray,
            modifier = Modifier.wrapContentSize()
        )
        TextField(
            value = content,
            onValueChange = onContentChange,
            textStyle = DessertTimeTheme.typography.textStyleRegular16,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = contentHint,
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    color = Black30
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = AzureRadiance,
                unfocusedIndicatorColor = Alto
            )
        )
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MaterialItemList(items: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp)
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            FlowRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items.forEach { material ->
                    MaterialItemRound(
                        categorySubName = material,
                        modifier = Modifier
                            .padding(end = 8.dp, bottom = 8.dp)
                            .clip(RoundedCornerShape(50))
                            .background(WildSand)
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
            style = DessertTimeTheme.typography.textStyleMedium14,
            color = DoveGray,
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WriteReviewScreenPreview() {
    ReviewWriteScreen()
}