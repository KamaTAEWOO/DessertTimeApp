package com.desserttime.core.di

import com.desserttime.core.network.qualifier.LoggingRetrofit
import com.desserttime.core.network.service.CategoryService
import com.desserttime.core.network.service.UserInfoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideCategoryService(
        @LoggingRetrofit retrofit: Retrofit
    ): CategoryService = retrofit.create()

    @Provides
    @Singleton
    fun provideUserInfoService(
        @LoggingRetrofit retrofit: Retrofit
    ): UserInfoService = retrofit.create()
}
