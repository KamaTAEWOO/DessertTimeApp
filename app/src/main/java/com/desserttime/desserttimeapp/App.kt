package com.desserttime.desserttimeapp

import android.app.Application
import com.desserttime.core.BuildConfig
import com.google.firebase.FirebaseApp
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

private const val ClientName = "DessertTime"

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        KakaoSdk.init(this, BuildConfig.KAKAO_API_KEY)
        NaverIdLoginSDK.initialize(this, BuildConfig.NAVER_API_KEY, BuildConfig.NAVER_API_SECRET, ClientName)
        FirebaseApp.initializeApp(this)
    }
}
