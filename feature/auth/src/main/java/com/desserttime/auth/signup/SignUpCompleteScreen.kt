package com.desserttime.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.desserttime.auth.AuthViewModel
import com.desserttime.design.R
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.ui.common.CommonUi
import kotlinx.coroutines.delay

@Composable
fun SignUpCompleteScreen(
    onTimeout: () -> Unit,
    authViewModel: AuthViewModel
) {
    authViewModel.checkValidation(authViewModel.snsId.value)
    var count by remember { mutableIntStateOf(3) }

    LaunchedEffect(Unit) {
        repeat(3) {
            count = 3 - it
            delay(1000L)
        }
        onTimeout()
    }

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
        CommonUi.PageCountDown(count)
    }
}
