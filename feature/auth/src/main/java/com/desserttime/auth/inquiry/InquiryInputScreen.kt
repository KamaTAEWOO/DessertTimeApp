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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.AzureRadiance
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.DoveGray
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.CommonUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InquiryInputScreen(
    onNavigateToInquiryInput: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        var emailText by remember { mutableStateOf("") }
        var contentText by remember { mutableStateOf("") }

        Spacer(Modifier.padding(top = 54.dp))
        // Row -> 이미지, Text
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
        Spacer(Modifier.padding(top = 40.dp))
        Text(
            text = stringResource(id = R.string.txt_inquiry_email),
            style = DessertTimeTheme.typography.textStyleBold14,
            color = DoveGray,
        )
        Spacer(Modifier.padding(top = 8.dp))
        //EditText
        TextField(
            value = emailText,
            onValueChange = { newText -> emailText = newText },
            textStyle = DessertTimeTheme.typography.textStyleRegular16,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.txt_inquiry_email_hint),
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    color = Color.Gray, // Adjust the placeholder text color if needed
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedIndicatorColor = AzureRadiance,
                unfocusedIndicatorColor = Black30,
            )
        )
        Spacer(Modifier.padding(top = 20.dp))
        Text(
            text = stringResource(id = R.string.txt_inquiry_content),
            style = DessertTimeTheme.typography.textStyleBold14,
            color = DoveGray,
        )
        Spacer(Modifier.padding(top = 8.dp))
        // outlineTextField
        OutlinedTextField(
            value = contentText,
            onValueChange = { newText -> contentText = newText },
            textStyle = DessertTimeTheme.typography.textStyleRegular16,
            modifier = Modifier
                .fillMaxWidth()
                .height(108.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.txt_inquiry_content_hint),
                    style = DessertTimeTheme.typography.textStyleRegular16,
                    color = Black30,
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = WildSand,
                focusedIndicatorColor = AzureRadiance,
                unfocusedIndicatorColor = Black30,
            )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 58.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            CommonUi.NextButton(
                text = stringResource(R.string.txt_inquiry_content_send),
                onClick = onNavigateToInquiryInput,
                background = MainColor,
                textColor = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    InquiryInputScreen(
        onNavigateToInquiryInput = {},
        onBack = {}
    )
}