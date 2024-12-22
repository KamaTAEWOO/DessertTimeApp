package com.desserttime.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.desserttime.auth.AuthViewModel
import com.desserttime.design.R
import com.desserttime.design.theme.AltoAgree
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.Black50
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.Gallery
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.CommonUi

@Composable
fun SignUpAgreeScreen(
    onNavigateToSignUpInput: () -> Unit = {},
    authViewModel: AuthViewModel
) {
    val isLoading by authViewModel.isLoading
    var buttonColor = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(start = 32.dp)
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(Modifier.padding(top = 70.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_cake),
            contentDescription = "img_login_logo",
            modifier = Modifier
                .size(56.dp, 56.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(Modifier.padding(top = 24.dp))
        TitleText()
        Spacer(Modifier.padding(top = 48.dp))
        buttonColor = allAgreeRadioButtonGroup(authViewModel)
    }

    AgreeNextButton(onNavigateToSignUpInput, buttonColor)

    if (isLoading) {
        CommonUi.LoadingScreen()
    }
}

@Composable
fun TitleText() {
    Column {
        val text = stringResource(R.string.txt_title_dessert_time)
        val boldText = "디저트타임"

        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append(boldText)
            }
            append(text.removePrefix(boldText))
        }

        Text(
            text = annotatedString,
            style = DessertTimeTheme.typography.textStyleRegular30,
            color = Color.Black
        )
    }
}

@Composable
fun allAgreeRadioButtonGroup(authViewModel: AuthViewModel): MutableState<Boolean> {
    val options = listOf(
        stringResource(R.string.txt_all_agree),
        stringResource(R.string.txt_all_agree_detail1),
        stringResource(R.string.txt_all_agree_detail2),
        stringResource(R.string.txt_all_agree_detail3)
    )

    val selectedOptions = remember { mutableStateListOf(false, false, false, false) }
    val buttonColor = remember { mutableStateOf(false) }

    Column {
        options.forEachIndexed { index, text ->
            if (index == 1) {
                Spacer(Modifier.padding(top = 13.dp))
                CommonUi.GrayLine(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(end = 30.dp)
                        .background(Gallery)
                )
                Spacer(Modifier.padding(top = 29.dp))
            } else {
                Spacer(Modifier.padding(top = 29.dp))
            }

            AgreeList(selectedOptions, index, text)
        }
    }

    buttonColor.value = getButtonState(selectedOptions)
    saveAgreeState(authViewModel, selectedOptions[3])

    return buttonColor
}

@Composable
private fun AgreeList(
    selectedOptions: SnapshotStateList<Boolean>,
    index: Int,
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { handleOptionClick(selectedOptions, index) } // Make the entire row clickable
    ) {
        CustomRadioButton(
            selected = selectedOptions[index],
            onClick = { handleOptionClick(selectedOptions, index) }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = DessertTimeTheme.typography.textStyleRegular16,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .wrapContentSize()
            )
            if (index != 0) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = null,
                    tint = Black50,
                    modifier = Modifier.wrapContentSize()
                )
            }
        }
    }
}

// 1번과 2번, 3번, 0번 선택 상태를 확인하는 함수
private fun getButtonState(selectedOptions: List<Boolean>): Boolean {
    val areFirstTwoSelected = selectedOptions[1] && selectedOptions[2]
    val isOnlyFirstSelected = selectedOptions[0] && !selectedOptions[1] && !selectedOptions[2] && !selectedOptions[3]

    return areFirstTwoSelected || isOnlyFirstSelected
}

// 선택 상태에 따른 데이터 저장 함수
private fun saveAgreeState(authViewModel: AuthViewModel, isThirdSelected: Boolean) {
    authViewModel.saveIsAgreeADData(if (isThirdSelected) "Y" else "N")
}

// 옵션 선택 로직 처리 함수
private fun handleOptionClick(
    selectedOptions: MutableList<Boolean>,
    index: Int
) {
    if (index == 0) {
        val allChecked = selectedOptions[0]
        for (i in selectedOptions.indices) {
            selectedOptions[i] = !allChecked
        }
    } else {
        selectedOptions[index] = !selectedOptions[index]
        selectedOptions[0] = selectedOptions.subList(1, selectedOptions.size).all { it }
    }
}

@Composable
fun CustomRadioButton(
    selected: Boolean,
    onClick: () -> Unit
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
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        if (selected) {
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                tint = Color.White, // 이미지 색상
                modifier = Modifier.size(12.dp)
            )
        }
    }
}

@Composable
private fun AgreeNextButton(
    onNavigateToSignUpInput: () -> Unit,
    buttonColor: MutableState<Boolean>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 58.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        CommonUi.NextButton(
            text = stringResource(R.string.txt_next),
            onClick = onNavigateToSignUpInput,
            background = if (buttonColor.value) MainColor else AltoAgree,
            textColor = if (buttonColor.value) Color.White else Black30,
            enabled = buttonColor.value
        )
    }
}
