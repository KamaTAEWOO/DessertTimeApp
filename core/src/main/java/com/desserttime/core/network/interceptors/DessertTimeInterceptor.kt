package com.desserttime.core.network.interceptors

import android.annotation.SuppressLint
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class DessertTimeInterceptor @Inject constructor() : Interceptor {

    @SuppressLint("SimpleDateFormat")
    override fun intercept(chain: Interceptor.Chain): Response {
        // 추후 구현

        val newRequest = chain.request()
            .newBuilder()
            .build()
        return chain.proceed(newRequest)
    }
}
