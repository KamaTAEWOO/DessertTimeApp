package com.desserttime.review

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.desserttime.design.R
import com.desserttime.design.theme.Alabaster
import com.desserttime.design.theme.Alto
import com.desserttime.design.theme.AltoAgree
import com.desserttime.design.theme.AzureRadiance
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.Black60
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.DoveGray
import com.desserttime.design.theme.DustyGray
import com.desserttime.design.theme.GrayChateau
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.MainColor20
import com.desserttime.design.theme.TundoraCategory
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.design.ui.common.CommonUi

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun ReviewWriteScreen(
    reviewViewModel: ReviewViewModel,
    onNavigateToReview: () -> Boolean
) {
    val reviewUiState by reviewViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()),
        topBar = { ReviewWriteAppBar(onNavigateToReview) },
        content = { paddingValues ->
            ReviewWriteContent(
                reviewUiState = reviewUiState,
                reviewViewModel = reviewViewModel,
                paddingValues = paddingValues
            )
        }
    )
}

@Composable
fun ReviewWriteAppBar(onNavigateToReview: () -> Boolean) {
    AppBarUi.AppBar(
        onBackClick = { onNavigateToReview() },
        title = stringResource(id = R.string.txt_bottom_review),
        onSearchClick = {},
        onBellClick = {}
    )
}

@Composable
fun ReviewWriteContent(
    reviewUiState: ReviewState,
    reviewViewModel: ReviewViewModel,
    paddingValues: PaddingValues
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                top = paddingValues.calculateTopPadding(),
                end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                bottom = 0.dp
            )
            .background(WildSand)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .verticalScroll(scrollState)
        ) {
            ReviewWriteInputFields(reviewUiState)
            ReviewWriteCategorySelection()
            ReviewWriteMaterialSelection(reviewUiState)
            ReviewWriteScoreInput(reviewUiState)
            ReviewWriteTextArea(reviewUiState)
            ReviewWriteMenuPicture()
            ReviewWriteCompleteButton(reviewUiState, reviewViewModel)
        }
    }
}

@Composable
fun ReviewWriteInputFields(reviewUiState: ReviewState) {
    var storeName by remember { mutableStateOf(TextFieldValue(reviewUiState.storeName)) }
    var menuName by remember { mutableStateOf(TextFieldValue(reviewUiState.storeMenu)) }

    InputField(
        title = stringResource(id = R.string.txt_review_write_store_name),
        value = storeName,
        hint = stringResource(id = R.string.txt_review_write_store_name_hint),
        onValueChange = { storeName = it }
    )

    InputField(
        title = stringResource(id = R.string.txt_review_write_menu_name),
        value = menuName,
        hint = stringResource(id = R.string.txt_review_write_menu_name_hint),
        onValueChange = { menuName = it }
    )
}

@Composable
fun InputField(
    title: String,
    value: TextFieldValue,
    hint: String,
    onValueChange: (TextFieldValue) -> Unit
) {
    Spacer(modifier = Modifier.height(20.dp))
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        EditTextBox(
            title = title,
            content = value,
            contentHint = hint,
            onContentChange = onValueChange
        )
    }
}

@Composable
fun ReviewWriteCategorySelection() {
    Spacer(modifier = Modifier.height(20.dp))
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
        Text(
            text = stringResource(id = R.string.txt_review_write_category),
            style = DessertTimeTheme.typography.textStyleBold14,
            color = DoveGray,
            modifier = Modifier.wrapContentSize()
        )
    }
    SearchDropdownList()
}

@Composable
fun ReviewWriteMaterialSelection(reviewUiState: ReviewState) {
    val materialMap = mapOf(
        1 to stringResource(id = R.string.txt_review_write_material_selection_1),
        2 to stringResource(id = R.string.txt_review_write_material_selection_2),
        3 to stringResource(id = R.string.txt_review_write_material_selection_3),
        4 to stringResource(id = R.string.txt_review_write_material_selection_4),
        5 to stringResource(id = R.string.txt_review_write_material_selection_5),
        6 to stringResource(id = R.string.txt_review_write_material_selection_6),
        7 to stringResource(id = R.string.txt_review_write_material_selection_7)
    )

    SectionTitle(stringResource(id = R.string.txt_review_write_material_selection))
    MaterialItemList(reviewUiState, materialMap)
}

@Composable
fun ReviewWriteScoreInput(reviewUiState: ReviewState) {
    SectionTitle(stringResource(id = R.string.txt_review_write_score))
    ScoreCheck(reviewUiState)
}

@Composable
fun ReviewWriteTextArea(reviewUiState: ReviewState) {
    var inputReviewBehind by remember { mutableStateOf(reviewUiState.storeContent ?: "") }

    SectionTitle(stringResource(id = R.string.txt_review_write_behind))
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(
        value = inputReviewBehind,
        onValueChange = {
            if (it.length <= 40) {
                inputReviewBehind = it
                reviewUiState.storeContent = it
            }
        },
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
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AzureRadiance,
            unfocusedBorderColor = Black30,
            focusedContainerColor = WildSand
        )
    )
    WordCountIndicator(inputReviewBehind.length)
}

@Composable
fun WordCountIndicator(wordCount: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = if (wordCount == 40) TundoraCategory else MainColor)) {
                    append("$wordCount")
                }
                append(" / ")
                withStyle(style = SpanStyle(color = TundoraCategory)) {
                    append("최소 40자")
                }
            },
            style = DessertTimeTheme.typography.textStyleRegular10,
            modifier = Modifier.padding(end = 20.dp)
        )
    }
}

@Composable
fun ReviewWriteMenuPicture() {
    SectionTitle(stringResource(id = R.string.txt_review_write_menu_image))
    MenuPicture()
}

@Composable
fun ReviewWriteCompleteButton(reviewUiState: ReviewState, reviewViewModel: ReviewViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        CommonUi.NextButton(
            text = stringResource(R.string.txt_review_write_complete),
            onClick = { saveReviewWriteData(reviewViewModel) },
            background = if (reviewUiState.storeContent.length >= 2) MainColor else AltoAgree,
            textColor = if (reviewUiState.storeContent.length >= 2) Color.White else DustyGray,
            enabled = reviewUiState.storeContent.length >= 2
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun SectionTitle(title: String) {
    Spacer(modifier = Modifier.height(20.dp))
    Text(
        text = title,
        style = DessertTimeTheme.typography.textStyleBold14,
        color = DoveGray,
        modifier = Modifier.padding(start = 16.dp)
    )
}

@Composable
fun EditTextBox(
    title: String,
    content: TextFieldValue,
    contentHint: String,
    onContentChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // 제목을 표시하는 Text 컴포저블
        Text(
            text = title,
            style = DessertTimeTheme.typography.textStyleBold14,
            color = DoveGray,
            modifier = Modifier.wrapContentSize()
        )
        // CustomTextField를 사용하여 텍스트 입력 필드를 구성
        CommonUi.CustomTextField(
            textFieldValue = content,
            onValueChange = onContentChange,
            placeholderText = contentHint,
            placeholderStyle = DessertTimeTheme.typography.textStyleMedium14,
            containerColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedIndicatorColor = Alto,
            unfocusedIndicatorColor = Alto,
            textStyle = DessertTimeTheme.typography.textStyleMedium14,
            underlineThickness = 1.dp,
            paddingVertical = 0.dp, // 텍스트와 언더라인 사이의 간격 조절
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
        )
    }
}

@Composable
fun SearchDropdownList() {
    val suggestions = listOf(
        "Android",
        "Compose",
        "Kotlin",
        "Jetpack"
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<String?>(null) }
    var textFieldValue by remember { mutableStateOf(TextFieldValue("")) }

    // 입력된 텍스트와 일치하는 항목 필터링
    val filteredSuggestions = suggestions.filter {
        it.contains(textFieldValue.text, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        SearchTextField(
            textFieldValue = textFieldValue,
            onValueChange = {
                textFieldValue = it
                expanded = it.text.isNotEmpty()
            },
            placeholderText = stringResource(id = R.string.txt_review_write_category_hint)
        )

        if (expanded) {
            DropdownMenu(
                suggestions = filteredSuggestions,
                onItemSelected = { suggestion ->
                    selectedItem = suggestion
                    textFieldValue = TextFieldValue(suggestion)
                    expanded = false
                }
            )
        }
    }
}

@Composable
fun SearchTextField(
    textFieldValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeholderText: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        CommonUi.CustomTextField(
            textFieldValue = textFieldValue,
            onValueChange = onValueChange,
            placeholderText = placeholderText,
            placeholderStyle = DessertTimeTheme.typography.textStyleMedium14,
            containerColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedIndicatorColor = Alto,
            unfocusedIndicatorColor = Alto,
            textStyle = DessertTimeTheme.typography.textStyleMedium14,
            underlineThickness = 1.dp,
            paddingVertical = 0.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DropdownMenu(
    suggestions: List<String>,
    onItemSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp)
            .offset(y = (-28).dp)
            .border(1.dp, Alto, shape = RoundedCornerShape(4.dp))
            .background(WildSand, shape = RoundedCornerShape(4.dp))
            .clip(RoundedCornerShape(4.dp))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 41.dp * 5) // 최대 5개의 항목만 표시
        ) {
            items(suggestions.size) { index ->
                val suggestion = suggestions[index]
                SuggestionItem(
                    suggestion = suggestion,
                    query = suggestions.firstOrNull()?.substringBefore(' ') ?: "",
                    onClick = { onItemSelected(suggestion) }
                )
            }
        }

        CommonUi.Divide(color = Alto)
        NotFoundMessage()
    }
}

@Composable
fun SuggestionItem(
    suggestion: String,
    query: String,
    onClick: () -> Unit
) {
    val annotatedString = buildAnnotatedString {
        val startIndex = suggestion.indexOf(query, ignoreCase = true)
        if (startIndex >= 0) {
            append(suggestion.substring(0, startIndex))
            withStyle(style = SpanStyle(color = Color.Red)) {
                append(suggestion.substring(startIndex, startIndex + query.length))
            }
            append(suggestion.substring(startIndex + query.length))
        } else {
            append(suggestion)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(41.dp)
            .background(Alabaster)
            .padding(start = 16.dp, top = 10.dp, bottom = 10.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = annotatedString,
            style = DessertTimeTheme.typography.textStyleRegular14,
            color = Color.Black
        )
    }
}

@Composable
fun NotFoundMessage() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Text(
            text = stringResource(id = R.string.txt_review_write_category_not_find_category),
            style = DessertTimeTheme.typography.textStyleRegular12,
            color = Black60
        )
    }
}

@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MaterialItemList(
    reviewUiState: ReviewState,
    items: Map<Int, String>
) {
    val selectedItems = remember { mutableStateListOf<Int>() } // 번호를 저장하는 리스트

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
                items.forEach { (materialNumber, materialName) ->
                    val isClicked = selectedItems.contains(materialNumber) // 번호로 선택 여부 확인

                    MaterialItemRound(
                        categorySubName = materialName,
                        modifier = Modifier
                            .padding(end = 8.dp, bottom = 8.dp)
                            .clip(RoundedCornerShape(50))
                            .background(if (isClicked) MainColor20 else WildSand)
                            .clickable {
                                if (materialName == "기타") {
                                    selectedItems.clear()
                                    reviewUiState.storeMaterialList = emptyList()
                                }

                                // 선택된 항목 업데이트
                                if (isClicked) {
                                    selectedItems.remove(materialNumber)
                                } else {
                                    selectedItems.add(materialNumber)
                                }

                                // reviewUiState 업데이트
                                reviewUiState.storeMaterialList = selectedItems.toList()
                            },
                        textColor = if (isClicked) MainColor else DoveGray
                    )
                }
            }
        }
    }
}

@Composable
fun MaterialItemRound(
    categorySubName: String,
    modifier: Modifier = Modifier,
    textColor: Color = DoveGray
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(
            text = categorySubName,
            style = DessertTimeTheme.typography.textStyleMedium14,
            color = textColor,
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp)
        )
    }
}

@Composable
fun ScoreCheck(reviewUiState: ReviewState) {
    val starStates = remember { mutableStateListOf(false, false, false, false) }
    val count = remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
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
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        for (i in 0..index) {
                            starStates[i] = true
                        }
                        for (i in index + 1 until starStates.size) {
                            starStates[i] = false
                        }
                        count.intValue = index + 1
                    }
            )
        }
        // TODO : 추후 변경 예정
        Text(
            text = when (count.intValue) {
                0 -> "평가없음"
                1 -> "1점"
                2 -> "2점"
                3 -> "3점"
                else -> "4점"
            },
            style = DessertTimeTheme.typography.textStyleMedium14,
            color = GrayChateau,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
        )
        reviewUiState.storeScore = count.intValue
    }
}

@Composable
fun MenuPicture() {
    val context = LocalContext.current
    val selectedImages = remember { mutableStateListOf<Uri>() }
    var replaceIndex by remember { mutableIntStateOf(-1) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        handleImageSelection(context, uris, selectedImages, replaceIndex) {
            replaceIndex = -1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(selectedImages) { index, uri ->
                SelectedImageItem(
                    uri = uri,
                    isRepresentative = index == 0,
                    onClickReplace = {
                        replaceIndex = index
                        imagePickerLauncher.launch("image/*")
                    },
                    onClickDelete = {
                        selectedImages.remove(uri)
                    }
                )
            }

            // Add the "Add Image" button as a separate item in the LazyRow
            if (selectedImages.size < 4) {
                item {
                    AddImageButton(
                        currentSize = selectedImages.size,
                        onClick = {
                            imagePickerLauncher.launch("image/*")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SelectedImageItem(
    uri: Uri,
    isRepresentative: Boolean,
    onClickReplace: () -> Unit,
    onClickDelete: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(76.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(9.dp))
            .clip(RoundedCornerShape(9.dp))
    ) {
        AsyncImage(
            model = uri,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClickReplace() }
        )

        if (isRepresentative) {
            RepresentativeBadge()
        }

        IconButton(
            onClick = onClickDelete,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .wrapContentSize()
                .offset(x = 10.dp, y = -12.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_cancel),
                contentDescription = stringResource(id = R.string.txt_cancel)
            )
        }
    }
}

@Composable
fun AddImageButton(
    currentSize: Int,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(76.dp)
            .background(WildSand)
            .border(1.dp, Color.Gray, RoundedCornerShape(9.dp))
            .clip(RoundedCornerShape(9.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = stringResource(id = R.string.img_review_write_menu_image_description),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = "($currentSize/4)",
                style = DessertTimeTheme.typography.textStyleRegular12,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun RepresentativeBadge() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(22.dp)
            .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(0.dp)),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            text = "대표사진",
            color = Color.White,
            style = DessertTimeTheme.typography.textStyleRegular12,
            modifier = Modifier.padding(bottom = 4.dp)
        )
    }
}

private fun handleImageSelection(
    context: Context,
    uris: List<Uri>,
    selectedImages: SnapshotStateList<Uri>,
    replaceIndex: Int,
    resetReplaceIndex: () -> Unit
) {
    if (replaceIndex != -1) {
        if (uris.isNotEmpty()) {
            selectedImages[replaceIndex] = uris[0]
        }
        resetReplaceIndex()
    } else {
        if (uris.size + selectedImages.size <= 4) {
            selectedImages.addAll(uris)
        } else {
            Toast.makeText(context, "You can only select up to 4 images.", Toast.LENGTH_SHORT)
                .show()
        }
    }
}

fun saveReviewWriteData(reviewViewModel: ReviewViewModel) {
    reviewViewModel.saveReviewWriteData()
}
