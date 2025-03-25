package com.example.myapplication.ui.home

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

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "todos_prefs")
class MyPreferences(private val context: Context) {




    private val TODOS_KEY = stringPreferencesKey(auth.currentUser?.uid.toString())


    suspend fun updateTodos(list: List<Todo>) {
        val json = Gson().toJson(list)
        context.dataStore.edit { preferences ->
            preferences[TODOS_KEY] = json
        }


    }

    fun getTodos(): Flow<List<Todo>> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[TODOS_KEY] ?: "[]"
            val type = object : TypeToken<List<Todo>>() {}.type
            Gson().fromJson(json, type) ?: emptyList()
        }
    }


}