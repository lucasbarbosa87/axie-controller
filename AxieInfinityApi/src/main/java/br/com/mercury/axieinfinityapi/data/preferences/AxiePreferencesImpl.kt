package br.com.mercury.axieinfinityapi.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class AxiePreferencesImpl(val context: Context) : AxiePreferences {

    private val Context.dataStore by preferencesDataStore("app_preferences")

    override fun getBearerToken(): String {
        return runBlocking {
            val data = context.dataStore.data.first()
            return@runBlocking data[BEARER_TOKEN] ?: ""
        }
    }

    override fun setBearerToken(value: String) {
        runBlocking {
            context.dataStore.edit { settings ->
                settings[BEARER_TOKEN] = value
            }
        }
    }

    companion object PreferencesKeys {
        val BEARER_TOKEN = stringPreferencesKey("account_bearer_token")
    }
}