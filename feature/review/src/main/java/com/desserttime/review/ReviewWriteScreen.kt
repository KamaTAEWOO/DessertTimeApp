package com.desserttime.review

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.Alabaster
import com.desserttime.design.theme.Alto
import com.desserttime.design.theme.AltoAgree
import com.desserttime.design.theme.AzureRadiance
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.DoveGray
import com.desserttime.design.theme.DustyGray
import com.desserttime.design.theme.TundoraCategory
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.design.ui.common.CommonUi

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ReviewWriteScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState) // Add vertical scrolling
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
                onContentChange = { newText -> inputMenuName = newText }
            )
        }
        // 카테고리
        Box(modifier = Modifier.padding(top = 20.dp))
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Text(
                text = stringResource(id = R.string.txt_review_write_category),
                style = DessertTimeTheme.typography.textStyleBold14,
                color = DoveGray,
                modifier = Modifier.wrapContentSize()
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .border(0.dp, Color.Transparent)
                .shadow(0.dp, RoundedCornerShape(0.dp)),
            color = Color.Transparent
        ) {
            DropdownExample()
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
        Spacer(modifier = Modifier.padding(top = 12.dp))
        Text(
            text = stringResource(id = R.string.txt_review_write_score),
            style = DessertTimeTheme.typography.textStyleBold14,
            color = DoveGray,
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 16.dp)
        )
        Box(modifier = Modifier.padding(top = 8.dp))
        ScoreCheck()
        // 후기 작성
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Text(
            text = stringResource(id = R.string.txt_review_write_behind),
            style = DessertTimeTheme.typography.textStyleBold14,
            color = DoveGray,
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        OutlinedTextField(
            value = inputReviewBehind,
            onValueChange = { newText -> inputReviewBehind = newText },
            textStyle = DessertTimeTheme.typography.textStyleRegular16,
            modifier = Modifier
                .fillMaxWidth()
                .height(108.dp)
                .padding(horizontal = 16.dp),
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
                unfocusedIndicatorColor = Black30
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp) // Column 전체에 위쪽 패딩 추가
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End // 오른쪽 정렬
            ) {
                Text(
                    text = stringResource(id = R.string.txt_review_write_text_count),
                    style = DessertTimeTheme.typography.textStyleRegular10,
                    color = TundoraCategory,
                    modifier = Modifier.padding(end = 20.dp) // 오른쪽 패딩 추가
                )
            }
        }
        // 메뉴 사진
        Spacer(modifier = Modifier.padding(top = 5.dp))
        Text(
            text = stringResource(id = R.string.txt_review_write_menu_image),
            style = DessertTimeTheme.typography.textStyleBold14,
            color = DoveGray,
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.padding(top = 8.dp))
        MenuPicture()
        // 작성완료
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            CommonUi.NextButton(
                text = stringResource(R.string.txt_review_write_complete),
                onClick = {},
                background = AltoAgree,
                textColor = DustyGray
            )
        }
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

@Composable
fun DropdownExample() {
    val suggestions = listOf("Android", "Compose", "Kotlin", "Jetpack")
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier
        .padding(16.dp)
        .border(0.dp, Color.Transparent)
        .shadow(0.dp, RoundedCornerShape(0.dp)),
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .border(0.dp, Color.Transparent)
            .shadow(0.dp, RoundedCornerShape(0.dp)),
        ) {
            Button(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = selectedItem ?: "Select an item")
            }
            Box(modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 5.dp)
                .border(0.dp, Color.Transparent) // Remove border
                .shadow(0.dp, RoundedCornerShape(0.dp)) // Remove shadow
            ) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White) // Background color for the dropdown menu
                        .padding(top = 4.dp, start = 16.dp, end = 16.dp) // Padding to fit the menu
                        .border(0.dp, Color.Transparent) // Remove border
                        .shadow(0.dp, RoundedCornerShape(0.dp)) // Remove shadow
                ) {
                    suggestions.forEachIndexed { index, suggestion ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(41.dp)
                                .border(0.5.dp, Alto) // Border for each item
                                .background(Alabaster) // Background color for each item
                                .padding(start = 16.dp, top = 10.dp, bottom = 10.dp)
                                .clickable {
                                    selectedItem = suggestion
                                    expanded = false
                                }
                        ) {
                            Text(
                                text = suggestion,
                                style = DessertTimeTheme.typography.textStyleRegular14,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
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

@Composable
fun ScoreCheck() {
    val imageResource = painterResource(id = R.drawable.ic_star_off)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
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
fun MenuPicture() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .size(76.dp)
                .background(WildSand)
                .border(1.dp, Alto, RoundedCornerShape(9.dp))
                .clip(RoundedCornerShape(9.dp))
                .padding(top = 15.dp, start = 26.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = stringResource(id = R.string.img_review_write_menu_image_description),
                modifier = Modifier
                    .size(24.dp)
            )
            Text(
                text = stringResource(id = R.string.txt_review_write_menu_image_count),
                style = DessertTimeTheme.typography.textStyleMedium12,
                color = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WriteReviewScreenPreview() {
    ReviewWriteScreen()
}
