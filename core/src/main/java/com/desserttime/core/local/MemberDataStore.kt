package com.desserttime.core.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.desserttime.domain.model.MemberData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.google.gson.Gson

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "member_data_store")

class MemberDataStore(context: Context) {

    private val dataStore: DataStore<Preferences> = context.dataStore

    companion object {
        private val DATA_KEY = stringPreferencesKey("data")
    }

    // 데이터 저장
    suspend fun saveMemberData(data: MemberData) {
        dataStore.edit { preferences ->
            preferences[DATA_KEY] = Gson().toJson(data) // MemberData 객체를 JSON 문자열로 변환하여 저장
        }
    }

    // 데이터 읽기
    val memberData: Flow<MemberData> = dataStore.data
        .map { preferences ->
            val json = preferences[DATA_KEY] ?: "{}"
            Gson().fromJson(json, MemberData::class.java) // JSON 문자열을 MemberData 객체로 변환
        }
}
