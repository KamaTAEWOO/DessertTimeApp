package com.desserttime.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.AthensGray
import com.desserttime.design.theme.Black30
import com.desserttime.design.theme.Black60
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.Flamingo
import com.desserttime.design.theme.Gallery
import com.desserttime.design.theme.MainColor
import com.desserttime.design.theme.MainColor20
import com.desserttime.design.theme.MineShaft
import com.desserttime.design.theme.Tundora
import com.desserttime.design.theme.White
import com.desserttime.design.theme.WildSand
import com.desserttime.design.ui.common.CommonUi

@Composable
fun SignUpInputScreen(
    onNavigateToSignUpChoose: () -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 26.dp, end = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.padding(top = 66.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp) // Adjust height to ensure visibility
                .border(
                    1.dp,
                    AthensGray,
                    RoundedCornerShape(10.dp)
                ) // Use Color.Gray if Athens_Gray is not defined
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        AthensGray,
                        RoundedCornerShape(10.dp)
                    ) // Use Color.Gray if Athens_Gray is not defined
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f) // Width is 50% of the parent
                    .fillMaxHeight() // Full height
                    .background(
                        Flamingo,
                        RoundedCornerShape(10.dp)
                    ) // Use Color.Red if Flamingo is not defined
            )
        }
        Spacer(Modifier.padding(top = 28.dp))
        Text(
            text = stringResource(id = R.string.txt_add_input),
            style = DessertTimeTheme.typography.textStyleBold26,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.Start)
        )
        Spacer(Modifier.padding(top = 6.dp))
        Text(
            text = stringResource(id = R.string.txt_add_description),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = Black60,
            modifier = Modifier
                .align(Alignment.Start)
        )
        Spacer(Modifier.padding(top = 36.dp))
        Text(
            text = stringResource(id = R.string.txt_sex),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = MineShaft,
            modifier = Modifier
                .align(Alignment.Start)
        )
        Spacer(Modifier.padding(top = 8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { /* Handle Male button click */ },
                colors = ButtonDefaults.buttonColors(WildSand),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(WildSand, RoundedCornerShape(12.dp))
            ) {
                Text(
                    text = stringResource(R.string.txt_sex_man),
                    color = Tundora,
                    style = DessertTimeTheme.typography.textStyleRegular16
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { /* Handle Female button click */ },
                colors = ButtonDefaults.buttonColors(WildSand),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(WildSand, RoundedCornerShape(12.dp))
            ) {
                Text(
                    text = stringResource(R.string.txt_sex_woman),
                    color = Tundora,
                    style = DessertTimeTheme.typography.textStyleRegular16
                )
            }
        }
        Spacer(Modifier.padding(top = 20.dp))
        Text(
            text = stringResource(id = R.string.txt_birth),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = MineShaft,
            modifier = Modifier
                .align(Alignment.Start)
        )
        Spacer(Modifier.padding(top = 8.dp))
        Button(
            onClick = { /* Handle button click */ },
            colors = ButtonDefaults.buttonColors(White),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Gallery, RoundedCornerShape(12.dp))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // 텍스트와 이미지를 양 끝에 배치
            ) {
                Text(
                    text = stringResource(R.string.txt_birth_hint),
                    color = Black30,
                    style = DessertTimeTheme.typography.textStyleRegular16
                )
                Image(
                    painter = painterResource(R.drawable.ic_calendar), // 이미지 리소스를 사용
                    contentDescription = stringResource(R.string.txt_birth_description),
                    modifier = Modifier.size(24.dp) // 이미지 크기 조정
                )
            }
        }
        Spacer(Modifier.padding(top = 20.dp))
        Text(
            text = stringResource(id = R.string.txt_address),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = MineShaft,
            modifier = Modifier
                .align(Alignment.Start)
        )
        Spacer(Modifier.padding(top = 8.dp))
        Button(
            onClick = { /* Handle button click */ },
            colors = ButtonDefaults.buttonColors(White),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(1.dp, Gallery, RoundedCornerShape(12.dp))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween // 텍스트와 이미지를 양 끝에 배치
            ) {
                Text(
                    text = stringResource(R.string.txt_address_hint),
                    color = Black30,
                    style = DessertTimeTheme.typography.textStyleRegular16
                )
                Image(
                    painter = painterResource(R.drawable.ic_search), // 이미지 리소스를 사용
                    contentDescription = stringResource(R.string.txt_address_description),
                    modifier = Modifier.size(24.dp) // 이미지 크기 조정
                )
            }
        }
        Spacer(Modifier.padding(top = 188.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 58.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            CommonUi.NextButton(
                text = stringResource(R.string.txt_next),
                onClick = onNavigateToSignUpChoose,
                background = MainColor20,
                textColor = MainColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpInputScreen() {
    SignUpInputScreen({}, {})
}