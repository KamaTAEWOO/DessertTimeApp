package com.desserttime.auth.inquiry

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.desserttime.auth.AuthViewModel
import com.desserttime.design.R
import com.desserttime.design.theme.AzureRadiance
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.DoveGray
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.CommonUi

@Composable
fun InquiryInputScreen(
    onNavigateToInquiryComplete: () -> Unit,
    onBack: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        var emailText by remember { mutableStateOf(TextFieldValue("")) }
        var contentText by remember { mutableStateOf("") }

        Spacer(Modifier.padding(top = 54.dp))
        TitleSection(onBack)
        Spacer(Modifier.padding(top = 40.dp))

        emailText = inquiryEmail(emailText)
        Spacer(Modifier.padding(top = 20.dp))

        contentText = inquiryContent(contentText)
        InquiryNextButton(emailText, contentText, authViewModel, onNavigateToInquiryComplete)
    }
}

@Composable
private fun inquiryContent(contentText: String): String {
    var contentText1 = contentText
    Text(
        text = stringResource(id = R.string.txt_inquiry_content),
        style = DessertTimeTheme.typography.textStyleBold14,
        color = DoveGray
    )
    Spacer(Modifier.padding(top = 8.dp))
    // outlineTextField
    OutlinedTextField(
        value = contentText1,
        onValueChange = { newText -> contentText1 = newText },
        textStyle = DessertTimeTheme.typography.textStyleRegular16,
        modifier = Modifier
            .fillMaxWidth()
            .height(108.dp),
        placeholder = {
            Text(
                text = stringResource(id = R.string.txt_inquiry_content_hint),
                style = DessertTimeTheme.typography.textStyleRegular16,
                color = Black30
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AzureRadiance,
            unfocusedBorderColor = Black30,
            focusedContainerColor = WildSand
        )
    )
    return contentText1
}

@Composable
private fun inquiryEmail(emailText: TextFieldValue): TextFieldValue {
    var emailText1 = emailText
    Text(
        text = stringResource(id = R.string.txt_inquiry_email),
        style = DessertTimeTheme.typography.textStyleBold14,
        color = DoveGray
    )
    Spacer(Modifier.padding(top = 8.dp))
    // EditText
    CommonUi.CustomTextField(
        textFieldValue = emailText1,
        onValueChange = { newText -> emailText1 = newText },
        placeholderText = stringResource(id = R.string.txt_inquiry_email_hint),
        placeholderStyle = DessertTimeTheme.typography.textStyleMedium16,
        containerColor = Color.Transparent,
        cursorColor = Color.Black,
        focusedIndicatorColor = AzureRadiance,
        unfocusedIndicatorColor = Black30,
        textStyle = DessertTimeTheme.typography.textStyleMedium16,
        underlineThickness = 1.dp,
        paddingVertical = 0.dp, // 텍스트와 언더라인 사이의 간격 조절
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
    )
    return emailText1
}

@Composable
private fun TitleSection(onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 18.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBack() },
                contentScale = ContentScale.Fit
            )
        }
        Text(
            text = stringResource(id = R.string.txt_inquiry_title),
            style = DessertTimeTheme.typography.textStyleBold20,
            modifier = Modifier
                .align(Alignment.Center) // Center the text within the Box
        )
    }
}

@Composable
private fun InquiryNextButton(
    emailText: TextFieldValue,
    contentText: String,
    authViewModel: AuthViewModel,
    onNavigateToInquiryComplete: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 58.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        CommonUi.NextButton(
            text = stringResource(R.string.txt_inquiry_content_send),
            onClick = {
                requestSaveInqueryData(
                    emailText.text,
                    contentText,
                    authViewModel,
                    onNavigateToInquiryComplete
                )
            },
            background = MainColor,
            textColor = Color.White,
            enabled = true
        )
    }
}

// 보내기 버튼 클릭 시 호출
fun requestSaveInqueryData(
    emailText: String,
    contentText: String,
    authViewModel: AuthViewModel,
    onNavigateToInquiryComplete: () -> Unit
) {
    authViewModel.requestSendInquiryData(emailText, contentText, onNavigateToInquiryComplete)
}
