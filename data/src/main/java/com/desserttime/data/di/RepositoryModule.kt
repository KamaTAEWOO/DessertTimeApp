package com.desserttime.data.di

import com.desserttime.core.local.MemberDataStore
import com.desserttime.data.repository.CategoryRepositoryImpl
import com.desserttime.data.repository.MemberInfoRepositoryImpl
import com.desserttime.domain.repository.CategoryRepository
import com.desserttime.domain.repository.MemberInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    @Singleton
    fun bindMemberInfoRepository(
        memberInfoRepositoryImpl: MemberInfoRepositoryImpl
    ): MemberInfoRepository
}
