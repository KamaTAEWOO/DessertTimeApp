package com.desserttime.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import com.desserttime.design.R
import com.desserttime.design.theme.AltoAgree
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.Gallery
import com.desserttime.design.ui.common.CommonUi
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SignUpAgreeScreen(
    onNavigateToSignUpInput: () -> Unit = {},
    onBack: () -> Unit
) {
    // SystemUiController를 사용하여 상태 바 색상 설정
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)

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
            AllAgreeRadioButtonGroup()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 58.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            //Spacer(Modifier.padding(top = 177.dp))
            CommonUi.NextButton(
                text = stringResource(R.string.txt_next),
                onClick = onNavigateToSignUpInput,
                background = AltoAgree,
                textColor = Black30
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
fun AllAgreeRadioButtonGroup() {
    val options = listOf(
        stringResource(R.string.txt_all_agree),
        stringResource(R.string.txt_all_agree_detail1),
        stringResource(R.string.txt_all_agree_detail2),
        stringResource(R.string.txt_all_agree_detail3))
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(options[0]) }

    Column {
        options.forEachIndexed { index, text ->
            if(index == 1) {
                Spacer(Modifier.padding(top = 13.dp))
                CommonUi.GrayLine(
                    Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(end = 30.dp)
                        .background(Gallery),
                )
                Spacer(Modifier.padding(top = 29.dp))
            } else {
                Spacer(Modifier.padding(top = 29.dp))
            }
            Row(
                //modifier = Modifier.padding(top = 13.dp)
            ) {
                RadioButton(
                    modifier = Modifier
                        .size(24.dp, 24.dp),
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .wrapContentSize()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SignUpAgreeScreen(
        onNavigateToSignUpInput = {},
        onBack = {}
    )
}