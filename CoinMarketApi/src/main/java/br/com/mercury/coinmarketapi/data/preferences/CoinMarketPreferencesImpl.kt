package br.com.mercury.coinmarketapi.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class CoinMarketPreferencesImpl(private val context: Context) : CoinMarketPreferences {

    private val Context.dataStore by preferencesDataStore("app_preferences")

    override fun getDollarValue(): Double = runBlocking {
        return@runBlocking context.dataStore.data.first()[DOLLAR_VALUE] ?: 0.0
    }

    override suspend fun saveDollarValue(value: Double) {
        context.dataStore.edit {
            it[DOLLAR_VALUE] = value
        }
    }


    companion object {
        val DOLLAR_VALUE = doublePreferencesKey("double_value")
    }

}