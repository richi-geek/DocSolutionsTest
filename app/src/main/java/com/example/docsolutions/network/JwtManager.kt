package com.example.docsolutions.network

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class JwtManager(context: Context) {
    // Create the dataStore and give it a name same as shared preferences
    private val dataStore = context.createDataStore(name = "user_prefs")

    // Create a key we will use them to store and retrieve the data
    companion object {
        val JWT_TOKEN_KEY = preferencesKey<String>("JWT_TOKEN")
    }

    // Store user data
    // refer to the data store and using edit
    // we can store values using the keys
    suspend fun storeSession(jwt: String) {
        dataStore.edit {
            it[JWT_TOKEN_KEY] = jwt
            // here it refers to the preferences we are editing
        }
    }

    // Create a jwt flow to retrieve jwt from the preferences
    // flow comes from the kotlin coroutine
    val jwtTokenFlow: Flow<String> = dataStore.data.map {
        it[JWT_TOKEN_KEY] ?: ""
    }

    // TODO: borrar si tokenFlow funciona bien
    /*fun getSession() : Flow<String> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { pref ->
                val jwtToken = pref[JWT_TOKEN_KEY] ?: ""
                jwtToken
            }*/
}