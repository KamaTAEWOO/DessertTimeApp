package com.desserttime.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.Alabaster
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.Gallery
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.Tundora
import com.desserttime.design.ui.common.AppBarUi

@Composable
fun MyPageScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppBarUi.AppBar(
                title = stringResource(id = R.string.txt_bottom_full_name_my_page),
                onSettingClick = {}
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color.White)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                ProfileSection()
                Spacer(modifier = Modifier.height(40.dp))
                NoticeSection()
            }
        }
    )
}

@Composable
fun ProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 66.dp)
            .height(220.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_no_login),
            contentDescription = stringResource(id = R.string.img_mypage_profile),
            modifier = Modifier
                .size(106.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
        CenteredTextBox(
            text = stringResource(id = R.string.txt_mypage_no_login_title),
            textStyle = DessertTimeTheme.typography.textStyleRegular14,
            textColor = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        LoginButton()
    }
}

@Composable
fun CenteredTextBox(
    text: String,
    textStyle: TextStyle,
    textColor: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = textStyle,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoginButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(140.dp)
                .height(38.dp)
                .border(1.dp, MainColor, shape = RoundedCornerShape(50.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.txt_mypage_go_login),
                color = MainColor,
                style = DessertTimeTheme.typography.textStyleRegular16,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun NoticeSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 24.dp)
            .border(1.dp, Gallery, shape = RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight() // Remove fixed height
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 20.dp) // Adjust padding for better spacing
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_notice),
                    contentDescription = stringResource(id = R.string.txt_mypage_notice),
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(16.dp)) // Better spacing between the image and text
                Text(
                    text = stringResource(id = R.string.txt_mypage_notice),
                    color = Tundora,
                    style = DessertTimeTheme.typography.textStyleMedium14,
                )
            }
            Divider(
                color = Gallery,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 20.dp) // Adjust padding for better spacing
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_event),
                    contentDescription = stringResource(id = R.string.txt_mypage_event),
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(16.dp)) // Better spacing between the image and text
                Text(
                    text = stringResource(id = R.string.txt_mypage_event),
                    color = Tundora,
                    style = DessertTimeTheme.typography.textStyleMedium14,
                )
            }
            Divider(
                color = Gallery,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp, horizontal = 20.dp) // Adjust padding for better spacing
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_inquire),
                    contentDescription = stringResource(id = R.string.txt_mypage_inquiry),
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(16.dp)) // Better spacing between the image and text
                Text(
                    text = stringResource(id = R.string.txt_mypage_inquiry),
                    color = Tundora,
                    style = DessertTimeTheme.typography.textStyleMedium14,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyPageScreenPreview() {
    MyPageScreen()
}
