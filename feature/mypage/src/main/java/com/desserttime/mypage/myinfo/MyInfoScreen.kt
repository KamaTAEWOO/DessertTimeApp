package com.desserttime.mypage.myinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.AzureRadiance
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.Gallery
import com.desserttime.design.theme.MineShaft
import com.desserttime.design.theme.SilverChalice
import com.desserttime.design.theme.Tundora
import com.desserttime.design.theme.Tundora60
import com.desserttime.design.theme.White
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.AppBarUi
import com.desserttime.design.ui.common.CommonUi

@Composable
fun MyInfoScreen () {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 26.dp),
        topBar = {
            AppBarUi.AppBar(
                onBackClick = { },
                title = stringResource(id = R.string.txt_my_info_title),
                onSaveClick = { },
                color = Black30
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
                    .background(Color.White)
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                OverlappingImages()
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 24.dp)
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Nickname()
                }
            }
        }
    )
}

@Composable
fun OverlappingImages() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_like_profile),
                contentDescription = stringResource(id = R.string.txt_my_info_nickname),
                modifier = Modifier
                    .size(106.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_modify_nickname),
                contentDescription = stringResource(id = R.string.txt_my_info_nickname),
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}

//@Composable
//fun MyInfoProfile() {
//    Column(
//        modifier = Modifier.fillMaxWidth(),
//        verticalArrangement = Arrangement.Center
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_like_profile),
//            contentDescription = stringResource(id = R.string.txt_my_info_nickname),
//            modifier = Modifier
//                .size(106.dp)
//                .align(Alignment.CenterHorizontally)
//        )
//    }
//}

@Composable
fun Nickname() {
    var nickname by remember { mutableStateOf(TextFieldValue("")) }

    Text(
        text = stringResource(id = R.string.txt_my_info_nickname),
        style = DessertTimeTheme.typography.textStyleRegular16,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(8.dp))
    NicknameInputWithCheck(
        nickname,
        onNicknameChange = { nickname = it },
        onCheckDuplicate = { }
    )
}

@Composable
fun NicknameInputWithCheck(
    nickname: TextFieldValue,
    onNicknameChange: (TextFieldValue) -> Unit,
    onCheckDuplicate: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Nickname Input Row with TextField and Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Gallery, RoundedCornerShape(12.dp))
                .padding(end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CommonUi.CustomTextField(
                textFieldValue = nickname,
                onValueChange = onNicknameChange,
                placeholderText = stringResource(id = R.string.txt_my_info_nickname_hint),
                placeholderStyle = DessertTimeTheme.typography.textStyleMedium16,
                containerColor = Color.Transparent,
                cursorColor = Color.Black,
                focusedIndicatorColor = AzureRadiance,
                unfocusedIndicatorColor = Color.Transparent,
                textStyle = DessertTimeTheme.typography.textStyleMedium16,
                underlineThickness = 1.dp,
                paddingVertical = 0.dp,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp) // Adjust TextField padding
            )

            Button(
                onClick = onCheckDuplicate,
                modifier = Modifier
                    .size(82.dp, 40.dp)
                    .align(Alignment.CenterVertically),
                colors = ButtonDefaults.buttonColors(WildSand),
                shape = RoundedCornerShape(6.dp),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.txt_my_info_nickname_check),
                    color = Tundora60,
                    style = DessertTimeTheme.typography.textStyleMedium14,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Gender Selection
        Text(
            text = stringResource(id = R.string.txt_sex),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = MineShaft,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(WildSand),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.txt_sex_man),
                    color = Tundora,
                    style = DessertTimeTheme.typography.textStyleRegular16
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(WildSand),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = stringResource(R.string.txt_sex_woman),
                    color = Tundora,
                    style = DessertTimeTheme.typography.textStyleRegular16
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Birth Date Selection
        Text(
            text = stringResource(id = R.string.txt_birth),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = MineShaft,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(White),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Gallery, RoundedCornerShape(12.dp))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.txt_birth_hint),
                    color = Black30,
                    style = DessertTimeTheme.typography.textStyleRegular16
                )

                Image(
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = stringResource(R.string.txt_birth_description),
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Address Selection
        Text(
            text = stringResource(id = R.string.txt_address),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = MineShaft,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(White),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Gallery, RoundedCornerShape(12.dp))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.txt_address_hint),
                    color = Black30,
                    style = DessertTimeTheme.typography.textStyleRegular16
                )

                Image(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = stringResource(R.string.txt_address_description),
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.txt_my_info_taste),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = MineShaft,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(White),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Gallery, RoundedCornerShape(12.dp))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.txt_my_info_taste_hint),
                    color = Black30,
                    style = DessertTimeTheme.typography.textStyleRegular16
                )

                Image(
                    painter = painterResource(R.drawable.ic_right_arrow),
                    contentDescription = stringResource(R.string.txt_my_info_taste_hint),
                    modifier = Modifier.size(30.dp),
                    colorFilter = ColorFilter.tint(SilverChalice)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyInfoScreenPreview() {
    MyInfoScreen()
}
