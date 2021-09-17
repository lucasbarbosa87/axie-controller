package br.com.mercury.coinmarketapi.repository

import android.util.Log
import br.com.mercury.coinmarketapi.data.local.CoinMarketDatabase
import br.com.mercury.coinmarketapi.data.local.model.AccountDb
import br.com.mercury.coinmarketapi.data.local.model.SlpCoinDb
import br.com.mercury.coinmarketapi.data.network.CoinMarketApi
import br.com.mercury.coinmarketapi.data.network.DollarApi
import br.com.mercury.coinmarketapi.data.preferences.CoinMarketPreferences
import br.com.mercury.coinmarketapi.di.dollarApiProvider
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient

class CoinMarketRepositoryImpl(
    private val coinMarketApi: CoinMarketApi,
    private val coinMarketDatabase: CoinMarketDatabase,
    private val preferences: CoinMarketPreferences,
    okHttpClient: OkHttpClient
) : CoinMarketRepository {

    private val dollarApi = dollarApiProvider(okHttpClient).create(DollarApi::class.java)

    override suspend fun getAccountInfoNetwork() {
        val accountInfo = coinMarketApi.getAccountInfo().accountInfo
        val account = AccountDb(accountInfo)
        coinMarketDatabase.accountDao().createAccountEntity(account)
    }

    override suspend fun getSmoothLovePotionValueNetwork() {
        val coinValue = coinMarketApi.getSlpInfo().slpCoin.slpCoin
        val coinDb = SlpCoinDb(coinValue)
        coinMarketDatabase.slpCoinDao().insertOrUpdateSlpCoin(coinDb)
    }

    override suspend fun overRideInformation() {
        getAccountInfoNetwork().let {
            if (checkIfAccountHasLimit(getAccountInfoLocal())) {
                withContext(Dispatchers.IO) {
                    getSmoothLovePotionValueNetwork()
                }
            }
        }
    }

    override suspend fun getCurrencyValueNetWork(termName: String) {
        try {
            val jsonBody = JsonObject()
            jsonBody.addProperty("method", "spotRateHistory")
            val jsonData = JsonObject()
            jsonData.addProperty("base", "USD")
            jsonData.addProperty("term", termName)
            jsonData.addProperty("period", "week")
            jsonBody.add("data", jsonData)
            val result = dollarApi.getDolarValue(jsonBody)
            val dollarResult = result.data
            preferences.saveDollarValue(dollarResult.currentInterbankRate)
        } catch (ex: Exception) {
            Log.e("erro", ex.toString())
        }
    }

    override suspend fun getDollarValue(): Double = preferences.getDollarValue()

    override suspend fun getSmoothLovePotionValueLocal() =
        coinMarketDatabase.slpCoinDao().getSlpCoin()

    private fun checkIfAccountHasLimit(account: AccountDb): Boolean {
        return when {
            account.requestsLeft == 0 -> false
            account.creditLimitDaily == account.creditsUsedDay -> false
            account.creditsLeftMonth == 0 -> false
            else -> true
        }
    }

    private suspend fun getAccountInfoLocal() = coinMarketDatabase.accountDao().getAccount()

}