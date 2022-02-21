package com.assignment.clientapp.presentation.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import com.assignment.domain.model.PostsDomainResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import java.io.IOException


private lateinit var dataStore: DataStore<Preferences>

private fun setupDataStore(context: Context) {
    dataStore = context.createDataStore(name = "settings")
}

suspend fun saveString(context: Context, key: String, value: String) {
    setupDataStore(context)
    val dataStoreKey = preferencesKey<String>(key)
    dataStore.edit { settings ->
        settings[dataStoreKey] = value
    }
}

suspend fun readString(context: Context, key: String): String? {
    setupDataStore(context)
    val dataStoreKey = preferencesKey<String>(key)
    val preferences = dataStore.data.first()
    return preferences[dataStoreKey]
}

suspend fun loadPostsListFromDataStore(context: Context, key: String): PostsDomainResponse? {
    val json: String? = readString(context, key)
    if (json.equals("", ignoreCase = true)) return null

    return try {
        getJsonObject(json, PostsDomainResponse::class.java)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

suspend fun savePostsListToDataStore(context: Context, key: String, value: PostsDomainResponse) {
    val gson = Gson()
    val json: String = gson.toJson(value)
    saveString(context, key, json)
}

fun getJsonObject(json: String?, type: Class<PostsDomainResponse>): PostsDomainResponse? {

    val gson = Gson()

    return gson.fromJson(json, type)
}

