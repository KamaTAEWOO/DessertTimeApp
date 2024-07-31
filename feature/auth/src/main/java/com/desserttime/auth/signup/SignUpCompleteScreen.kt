package com.desserttime.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.Black60
import com.desserttime.design.theme.DessertTimeTheme

@Composable
fun SignUpCompleteScreen(
    onNavigateToSignIn: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 26.dp, end = 26.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.padding(top = 198.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_login_logo),
            contentDescription = "img_login_logo",
            modifier = Modifier.size(171.dp, 64.6.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(Modifier.padding(top = 40.dp))
        Text(
            text = stringResource(id = R.string.txt_complete1),
            style = DessertTimeTheme.typography.textStyleRegular26,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.padding(top = 5.dp))
        Text(
            text = stringResource(id = R.string.txt_complete2),
            style = DessertTimeTheme.typography.textStyleRegular26,
            color = Color.Black,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 102.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = stringResource(id = R.string.txt_next_page),
                style = DessertTimeTheme.typography.textStyleRegular16,
                color = Black60,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable(onClick = onNavigateToSignIn) // @@2 Test
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignUpCompleteScreen() {
    SignUpCompleteScreen({})
}
