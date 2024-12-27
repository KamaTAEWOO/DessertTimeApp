package com.desserttime.desserttimeapp.splash

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.desserttime.core.utility.MemberDataManager
import com.desserttime.core.utility.SharedPreferencesManager
import com.desserttime.design.theme.MainColor
import com.desserttime.desserttimeapp.R

@Composable
fun SplashScreen(onNavigateToLogin: () -> Unit, onNavigateToHome: () -> Unit) {
    val context: Context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context)
    val memberData = sharedPreferencesManager.getMemberData()
    if (memberData != null) {
        MemberDataManager.saveData(memberData)
    }

    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(3000)
        if (MemberDataManager.token?.isNotEmpty() == true) {
            onNavigateToHome()
        } else {
            onNavigateToLogin()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainColor)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_splash_icon),
            contentDescription = R.string.app_name.toString(),
            modifier = Modifier
                .align(Alignment.Center)
                .wrapContentSize()
        )
    }
}
