package com.desserttime.core.di

import com.desserttime.core.BuildConfig
import com.desserttime.core.network.interceptors.DessertTimeInterceptor
import com.desserttime.core.network.qualifier.LoggingClient
import com.desserttime.core.network.qualifier.LoggingRetrofit
import com.desserttime.core.network.qualifier.RefreshTokenClient
import com.desserttime.core.network.qualifier.RefreshTokenRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    private val BASE_URL = BuildConfig.BASE_URL

    @LoggingRetrofit
    @Provides
    @Singleton
    fun provideLoggingRetrofit(
        @LoggingClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @RefreshTokenRetrofit
    @Provides
    @Singleton
    fun provideRefreshTokenRetrofit(
        @RefreshTokenClient okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @RefreshTokenClient
    @Provides
    @Singleton
    fun provideRefreshTokenClient(
        dessertTimeInterceptor: DessertTimeInterceptor,
        loggingInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(dessertTimeInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @LoggingClient
    @Provides
    @Singleton
    fun provideLoggingClient(
        loggingInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
}
