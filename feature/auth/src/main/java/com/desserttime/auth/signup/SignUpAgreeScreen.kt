package com.desserttime.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SignUpAgreeScreen(
    onNavigateToSignUpInput: () -> Unit = {},
    onBack: () -> Unit,
    authViewModel: AuthViewModel
) {
    // SystemUiController를 사용하여 상태 바 색상 설정
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)

    // 버튼 색상 변경
    var buttonColor = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(start = 32.dp)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 58.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            // Spacer(Modifier.padding(top = 177.dp))
            CommonUi.NextButton(
                text = stringResource(R.string.txt_next),
                onClick = onNavigateToSignUpInput,
                background = if (buttonColor.value) MainColor else AltoAgree,
                textColor = if (buttonColor.value) Color.White else Black30,
                enabled = buttonColor.value
            )
        }
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

    // 각 옵션의 선택 상태를 관리하는 리스트
    // TODO : 데이터 저장하기
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
            Row {
                CustomRadioButton(
                    selected = selectedOptions[index],
                    onClick = {
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
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 35.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = text,
                        style = DessertTimeTheme.typography.textStyleRegular16,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .wrapContentSize()
                    )
                    if(index != 0) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_right_arrow),
                            contentDescription = null,
                            tint = Black50, // 이미지 색상
                            modifier = Modifier
                                .wrapContentSize()
                        )
                    }
                }
            }
        }
    }

    val check = selectedOptions.any { it }
    buttonColor.value = check

    authViewModel.saveIsAgreeADData(if(selectedOptions[selectedOptions.size - 1]) "Y" else "N")

    return buttonColor
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SignUpAgreeScreen(
        onNavigateToSignUpInput = {},
        onBack = {},
        authViewModel = AuthViewModel()
    )
}
