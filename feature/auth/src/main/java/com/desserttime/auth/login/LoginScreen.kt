package com.desserttime.auth.login

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.desserttime.design.R
import com.desserttime.design.theme.Alto
import com.desserttime.design.theme.Black
import com.desserttime.design.theme.Black54
import com.desserttime.design.theme.DessertTimeTheme
import com.desserttime.design.theme.Emperor
import com.desserttime.design.theme.Gallery
import com.desserttime.design.theme.Malachite
import com.desserttime.design.theme.OsloGray
import com.desserttime.design.theme.Turbo
import com.desserttime.design.theme.White
import com.desserttime.design.ui.common.CommonUi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kakao.sdk.user.UserApiClient
import timber.log.Timber

private const val TAG = "LoginScreen"

@Composable
fun LoginScreen(
    onNavigateToSignUpAgree: () -> Unit = {},
    onNavigateToInquiryInput: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onBack: () -> Unit
) {
    val context = LocalContext.current

    // SystemUiController를 사용하여 상태 바 색상 설정
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.padding(top = 127.dp))
        // 로그인 화면 상단 로고 이미지
        Image(
            painter = painterResource(id = R.drawable.ic_login_logo),
            contentDescription = "img_login_logo",
            modifier = Modifier.size(171.dp, 64.6.dp),
            contentScale = ContentScale.FillBounds
        )
        Spacer(Modifier.padding(top = 45.4.dp))
        LoginTextAndLine()

        Spacer(Modifier.padding(top = 32.dp))
        // 카카오 버튼
        LoginButton(
            stringResource(id = R.string.txt_login_kakao),
            { loginWithKakao(context) },
            Turbo,
            Black,
            R.drawable.ic_kakao_logo,
            Turbo
        )
        Spacer(Modifier.padding(top = 12.dp))
        // 네이버 버튼
        LoginButton(
            stringResource(id = R.string.txt_login_naver),
            onNavigateToHome,
            Malachite,
            White,
            R.drawable.ic_naver_logo,
            Malachite
        )
        Spacer(Modifier.padding(top = 12.dp))
        // 구글 버튼
        LoginButton(
            stringResource(id = R.string.txt_login_google),
            {},
            White,
            Black54,
            R.drawable.ic_google_logo,
            Alto
        )
        Spacer(Modifier.padding(top = 28.dp))
        CommonUi.GrayLine(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(start = 30.dp, end = 30.dp)
                .background(Gallery)
        )
        Spacer(Modifier.padding(top = 48.dp))
        // 문의하기 버튼으로 변경
        Button(
            onClick = onNavigateToInquiryInput,
            colors = ButtonDefaults.buttonColors(White)
        ) {
            Text(
                text = stringResource(R.string.txt_login_question),
                style = DessertTimeTheme.typography.textStyleRegular16,
                color = Emperor
            )
        }
    }
}

// 버튼 component -> 매개변수는 text명, onClick, color, image
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
        colors = ButtonDefaults.buttonColors(background),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .size(320.dp, 52.dp)
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = R.string.txt_login_title),
                modifier = Modifier.size(16.dp, 16.dp),
                contentScale = ContentScale.FillBounds
            )
            Spacer(Modifier.width(8.dp))
            Text(
                text = text,
                style = DessertTimeTheme.typography.textStyleRegular14,
                color = textColor
            )
        }
    }
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
        // 로그인/회원가입
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

fun loginWithKakao(context: Context) {
    if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
        // Try KakaoTalk login first
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                // Handle error, fallback to Kakao account login if necessary
                Timber.i("$TAG Login failed: ${error.message}")

                // Optional: Fallback to web login
                loginWithKakaoAccount(context)
            } else if (token != null) {
                // Handle successful login
                Timber.i("$TAG Login successful. Token: ${token.accessToken}")
                fetchKakaoUserInfo()
            }
        }
    } else {
        // KakaoTalk is not installed, fallback to Kakao Account login
        loginWithKakaoAccount(context)
    }
}

fun loginWithKakaoAccount(context: Context) {
    UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
        if (error != null) {
            Timber.i("$TAG Login failed: ${error.message}")
        } else if (token != null) {
            Timber.i("$TAG Login successful. Token: ${token.accessToken}")
            fetchKakaoUserInfo()
        }
    }
}

fun fetchKakaoUserInfo() {
    UserApiClient.instance.me { user, error ->
        if (error != null) {
            Timber.i("$TAG Failed to get user info: ${error.message}")
        } else if (user != null) {
            val userId = user.id
            val userEmail = user.kakaoAccount?.email
            val userConnect = user.connectedAt // Login time
            Timber.i("$TAG User ID: $userId")
            Timber.i("$TAG User Email: $userEmail")
            Timber.i("$TAG User Connected At: $userConnect")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onNavigateToSignUpAgree = {},
        onNavigateToInquiryInput = {},
        onNavigateToHome = {},
        onBack = {}
    )
}
