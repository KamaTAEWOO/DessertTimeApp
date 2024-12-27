package com.desserttime.core.utility

import android.content.Context
import android.media.session.MediaSession.Token
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.desserttime.domain.model.TokenData
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferencesManager @Inject constructor(
    @ApplicationContext context: Context
) {
    private val gson = Gson()

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM) // AES256_GCM 방식 사용
        .build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        "desserttime_member_data",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveMemberData(memberData: TokenData) {
        val json = gson.toJson(memberData)
        sharedPreferences.edit()
            .putString("member_data", json)
            .apply()
    }

    fun getMemberData(): TokenData? {
        val json = sharedPreferences.getString("member_data", null)
        return if (json != null) {
            gson.fromJson(json, TokenData::class.java)
        } else {
            null
        }
    }
}
