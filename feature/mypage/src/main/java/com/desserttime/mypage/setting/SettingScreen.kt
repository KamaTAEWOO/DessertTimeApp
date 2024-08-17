package com.desserttime.mypage.setting

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.Gallery
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.design.ui.common.CommonUi

@Composable
fun SettingScreen(
    onNavigateToHome: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 26.dp),
        topBar = {
            AppBarUi.AppBar(
                { onBack() },
                title = stringResource(id = R.string.txt_mypage_setting_title)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = paddingValues.calculateStartPadding(LayoutDirection.Ltr),
                        top = paddingValues.calculateTopPadding(),
                        end = paddingValues.calculateEndPadding(LayoutDirection.Ltr),
                        bottom = 0.dp // Remove the bottom padding
                    )
                    .background(WildSand)
            ) {
                SettingContent()
            }
        },
        bottomBar = {
            BottomContent(onNavigateToHome)
        }
    )
}

@Composable
fun SettingContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        AlarmPush()
        TermsOfService()
        PrivacyPolicy()
    }
}

@Composable
fun CustomSwitch(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    // 스위치 크기와 속성
    val switchWidth = 54.dp
    val switchHeight = 30.dp
    val dotSize = 24.dp

    // 스위치 배경 색상
    val backgroundColor = if (isChecked) MainColor else Color.Gray

    // 스위치 dot 위치 조정 애니메이션
    val dotPosition by animateDpAsState(
        targetValue = if (isChecked) ((switchWidth - dotSize) - 6.dp) else 0.dp,
        label = ""
    )

    // 스위치 UI
    Box(
        modifier = Modifier
            .width(switchWidth)
            .height(switchHeight)
            .clip(RoundedCornerShape(15.dp))
            .background(backgroundColor)
            .clickable { onCheckedChange(!isChecked) } // 클릭 시 상태 변경
            .padding(3.dp) // dot 주변 여백
    ) {
        // dot (흰색 원)
        Box(
            modifier = Modifier
                .size(dotSize)
                .offset(x = dotPosition) // dot 위치 애니메이션
                .clip(CircleShape)
                .background(Color.White)
        )
    }
}

@Composable
fun AlarmPush() {
    var isPushEnabled by remember { mutableStateOf(false) }

    Divide()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 14.dp, bottom = 14.dp, start = 16.dp, end = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, // 수직 정렬
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.txt_mypage_setting_push),
                style = DessertTimeTheme.typography.textStyleRegular16,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            CustomSwitch(
                isChecked = isPushEnabled,
                onCheckedChange = { isChecked ->
                    isPushEnabled = isChecked // 상태 업데이트
                }
            )
        }
    }
}

@Composable
fun TermsOfService() {
    Divide()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .padding(top = 14.dp, bottom = 14.dp, start = 16.dp, end = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, // 수직 정렬
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.txt_mypage_setting_terms_of_use),
                color = Color.Black,
                style = DessertTimeTheme.typography.textStyleMedium16,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = stringResource(id = R.string.txt_next),
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}

@Composable
fun PrivacyPolicy() {
    Divide()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(58.dp)
            .padding(top = 14.dp, bottom = 14.dp, start = 16.dp, end = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, // 수직 정렬
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.txt_mypage_setting_privacy_policy),
                color = Color.Black,
                style = DessertTimeTheme.typography.textStyleMedium16,
                modifier = Modifier.weight(1f)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_right_arrow),
                contentDescription = stringResource(id = R.string.txt_next),
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
    Divide()
}

@Composable
fun Divide() {
    Divider(
        color = Gallery,
        thickness = 1.dp
    )
}

@Composable
fun BottomContent(
    onNavigateToHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 58.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.Bottom, // 하단에 요소 배치
        horizontalAlignment = Alignment.CenterHorizontally // 수평 가운데 정렬
    ) {
        CommonUi.NextButton(
            text = stringResource(R.string.txt_mypage_setting_logout),
            onClick = onNavigateToHome,
            background = MainColor, // 버튼 배경색
            textColor = Color.White // 버튼 텍스트 색상
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.txt_mypage_setting_withdrawal1),
                color = Black30, // 텍스트 색상
                style = DessertTimeTheme.typography.textStyleMedium12
            )
            Text(
                text = stringResource(id = R.string.txt_mypage_setting_withdrawal2),
                color = Black30, // 텍스트 색상
                style = DessertTimeTheme.typography.textStyleBold12,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { onNavigateToHome() }
            )
            Text(
                text = stringResource(id = R.string.txt_mypage_setting_withdrawal3),
                color = Black30, // 텍스트 색상
                style = DessertTimeTheme.typography.textStyleMedium12
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSettingContent() {
    SettingScreen({}, {})
}
