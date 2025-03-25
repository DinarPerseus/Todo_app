package com.example.myapplication.ui.profile

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val auth: FirebaseAuth = FirebaseAuth.getInstance()

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")
class ProfilePreferences(private val context: Context) {




    private val USER_KEY = stringPreferencesKey(auth.currentUser?.uid.toString())


    suspend fun updateUserInfo(userinfo : UserInfo ) {
        val json = Gson().toJson(userinfo)
        context.dataStore.edit { preferences ->
            preferences[USER_KEY] = json
        }


    }

    fun getUserInfo(): Flow<UserInfo> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[USER_KEY] ?: ""
            val type = object : TypeToken<UserInfo>() {}.type
            Gson().fromJson(json, type) ?: UserInfo()

        }
    }


}