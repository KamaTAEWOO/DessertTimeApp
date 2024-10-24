package com.desserttime.core.di

import com.desserttime.core.network.qualifier.LoggingRetrofit
import com.desserttime.core.network.service.CategoryService
import com.desserttime.core.network.service.LikeService
import com.desserttime.core.network.service.MemberInfoService
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
    fun provideMemberInfoService(
        @LoggingRetrofit retrofit: Retrofit
    ): MemberInfoService = retrofit.create()

    @Provides
    @Singleton
    fun provideLikeService(
        @LoggingRetrofit retrofit: Retrofit
    ): LikeService = retrofit.create()
}
