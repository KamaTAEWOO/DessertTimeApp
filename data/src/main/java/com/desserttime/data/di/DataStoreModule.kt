package com.desserttime.data.di

import android.content.Context
import com.desserttime.core.local.MemberDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideMemberDataStore(context: Context): MemberDataStore {
        return MemberDataStore(context)
    }
}
