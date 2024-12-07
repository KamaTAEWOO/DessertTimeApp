package com.desserttime.auth.login

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.desserttime.auth.AuthViewModel
import com.desserttime.auth.login.google.GoogleLoginInit
import com.desserttime.design.R
import com.desserttime.design.theme.*
import com.desserttime.design.ui.common.CommonUi
import com.desserttime.domain.model.LoginMethodData
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onNavigateToSignUpAgree: () -> Unit = {},
    onNavigateToInquiryInput: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
) {
    SetStatusBarColor(White)
    val context = LocalContext.current
    val isLoading by authViewModel.isLoading

    LoginScreenContent(
        context = context,
        isLoading = isLoading,
        onNavigateToSignUpAgree = onNavigateToSignUpAgree,
        onNavigateToInquiryInput = onNavigateToInquiryInput,
        onNavigateToHome = onNavigateToHome,
        onLoginWithKakao = {
            authViewModel.loginWithLogic(
                LoginMethodData.KAKAO,
                context,
                onNavigateToSignUpAgree,
                onNavigateToHome
            )
        },
        onLoginWithNaver = {
            authViewModel.loginWithLogic(
                LoginMethodData.NAVER,
                context,
                onNavigateToSignUpAgree,
                onNavigateToHome
            )
        },
        onLoginWithGoogle = {
            authViewModel.loginWithLogic(
                LoginMethodData.GOOGLE,
                context,
                onNavigateToSignUpAgree,
                onNavigateToHome
            )
        }
    )
}

@Composable
fun LoginScreenContent(
    context: Context,
    isLoading: Boolean,
    onNavigateToSignUpAgree: () -> Unit,
    onNavigateToInquiryInput: () -> Unit,
    onNavigateToHome: () -> Unit,
    onLoginWithKakao: () -> Unit,
    onLoginWithNaver: () -> Unit,
    onLoginWithGoogle: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            AppCloseButton(context)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                LoginHeader()
                Spacer(modifier = Modifier.height(45.4.dp))
                LoginTextAndLine()
                Spacer(modifier = Modifier.height(32.dp))
                LoginButtonsSection(
                    onLoginWithKakao = onLoginWithKakao,
                    onLoginWithNaver = onLoginWithNaver,
                    onLoginWithGoogle = onLoginWithGoogle
                )
                Spacer(modifier = Modifier.height(28.dp))
                CommonUi.GrayLine(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .padding(start = 30.dp, end = 30.dp)
                        .background(Gallery)
                )
                Spacer(modifier = Modifier.height(48.dp))
                InquiryActionButton(onClick = onNavigateToInquiryInput)
            }

            if (isLoading) {
                LoadingOverlay()
            }
        },
        bottomBar = {}
    )
}

@Composable
fun AppCloseButton(context: Context) {
    val activity = context as Activity

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 40.dp, horizontal = 20.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_app_close),
            contentDescription = "Close Button",
            modifier = Modifier
                .size(24.dp)
                .clickable { activity.finish() },
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun LoginHeader() {
    Image(
        painter = painterResource(id = R.drawable.ic_login_logo),
        contentDescription = "Login Logo",
        modifier = Modifier
            .size(width = 171.dp, height = 64.6.dp),
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun LoginTextAndLine() {
    Row(
        modifier = Modifier
            .height(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(108.dp, 1.dp)
                .background(Gallery)
        )
        Spacer(Modifier.padding(start = 4.dp))
        Text(
            text = stringResource(R.string.txt_login_title),
            style = DessertTimeTheme.typography.textStyleRegular14,
            color = OsloGray
        )
        Spacer(Modifier.padding(start = 4.dp))
        Box(
            modifier = Modifier
                .size(108.dp, 1.dp)
                .background(Gallery)
        )
    }
}

@Composable
fun LoginButtonsSection(
    onLoginWithKakao: () -> Unit,
    onLoginWithNaver: () -> Unit,
    onLoginWithGoogle: () -> Unit
) {
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LoginButton(
            text = stringResource(id = R.string.txt_login_kakao),
            onClick = onLoginWithKakao,
            background = Turbo,
            textColor = Black,
            image = R.drawable.ic_kakao_logo,
            borderColor = Turbo
        )
        Spacer(modifier = Modifier.height(12.dp))
        LoginButton(
            text = stringResource(id = R.string.txt_login_naver),
            onClick = onLoginWithNaver,
            background = Malachite,
            textColor = White,
            image = R.drawable.ic_naver_logo,
            borderColor = Malachite
        )
        Spacer(modifier = Modifier.height(12.dp))
        GoogleLoginInit(context)
        LoginButton(
            text = stringResource(id = R.string.txt_login_google),
            onClick = onLoginWithGoogle,
            background = White,
            textColor = Black54,
            image = R.drawable.ic_google_logo,
            borderColor = Alto
        )
    }
}

@Composable
fun LoginButton(
    text: String,
    onClick: () -> Unit = {},
    background: Color,
    textColor: Color,
    image: Int,
    borderColor: Color
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = background),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .height(52.dp)
            .fillMaxWidth(0.8f) // 80% 너비
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "$text Logo",
                modifier = Modifier.size(16.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = DessertTimeTheme.typography.textStyleRegular14,
                color = textColor
            )
        }
    }
}

@Composable
fun InquiryActionButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = White),
        modifier = Modifier
            .height(48.dp)
            .fillMaxWidth(0.8f) // 80% 너비
    ) {
        Text(
            text = stringResource(R.string.txt_login_question),
            style = DessertTimeTheme.typography.textStyleRegular16,
            color = Emperor
        )
    }
}

@Composable
fun LoadingOverlay() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        CommonUi.LottieAnimationDemo()
    }
}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val context: Context = LocalContext.current

    LoginScreenContent(
        context,
        isLoading = false,
        onNavigateToSignUpAgree = {},
        onNavigateToInquiryInput = {},
        onNavigateToHome = {},
        onLoginWithKakao = {},
        onLoginWithNaver = {},
        onLoginWithGoogle = {}
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenLoadingPreview() {
    val context: Context = LocalContext.current

    LoginScreenContent(
        context,
        isLoading = true,
        onNavigateToSignUpAgree = {},
        onNavigateToInquiryInput = {},
        onNavigateToHome = {},
        onLoginWithKakao = {},
        onLoginWithNaver = {},
        onLoginWithGoogle = {}
    )
}