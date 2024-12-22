package com.desserttime.core.di

import android.content.Context
import com.desserttime.core.BuildConfig
import com.desserttime.core.BuildConfig.BASE_URL
import com.desserttime.core.R
import com.desserttime.core.network.interceptors.DessertTimeInterceptor
import com.desserttime.core.network.qualifier.LoggingClient
import com.desserttime.core.network.qualifier.LoggingRetrofit
import com.desserttime.core.network.qualifier.RefreshTokenClient
import com.desserttime.core.network.qualifier.RefreshTokenRetrofit
import com.desserttime.core.utility.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @LoggingRetrofit
    @Provides
    @Singleton
    fun provideLoggingRetrofit(
        @LoggingClient okHttpClient: OkHttpClient,
        @ApplicationContext context: Context
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(context.getString(R.string.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @RefreshTokenRetrofit
    @Provides
    @Singleton
    fun provideRefreshTokenRetrofit(
        @RefreshTokenClient okHttpClient: OkHttpClient,
        @ApplicationContext context: Context
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(context.getString(R.string.BASE_URL))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @RefreshTokenClient
    @Provides
    @Singleton
    fun provideRefreshTokenClient(
        dessertTimeInterceptor: DessertTimeInterceptor,
        loggingInterceptor: Interceptor,
        sharedPreferencesManager: SharedPreferencesManager
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(dessertTimeInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val token = sharedPreferencesManager.getToken()
                val newRequest = originalRequest.newBuilder()
                    .header("Authorized", "Bearer $token")
                    .build()
                chain.proceed(newRequest)
            }
            .build()

    @LoggingClient
    @Provides
    @Singleton
    fun provideLoggingClient(
        loggingInterceptor: Interceptor,
        sharedPreferencesManager: SharedPreferencesManager
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val token = sharedPreferencesManager.getToken() ?: ""
                val newRequest = originalRequest.newBuilder()
                    .header("Authorized", "Bearer $token")
                    .build()
                chain.proceed(newRequest)
            }
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
}
