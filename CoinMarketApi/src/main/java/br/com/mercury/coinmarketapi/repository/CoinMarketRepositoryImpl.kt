package br.com.mercury.coinmarketapi.repository

import br.com.mercury.coinmarketapi.data.local.CoinMarketDatabase
import br.com.mercury.coinmarketapi.data.local.model.AccountDb
import br.com.mercury.coinmarketapi.data.local.model.SlpCoinDb
import br.com.mercury.coinmarketapi.data.network.model.DolarResponse
import br.com.mercury.coinmarketapi.data.network.model.DollarData
import br.com.mercury.coinmarketapi.di.provideRetrofit
import br.com.mercury.coinmarketapi.model.AccountInfo
import br.com.mercury.coinmarketapi.network.CoinMarketApi
import br.com.mercury.coinmarketapi.network.DollarApi
import com.google.gson.JsonObject
import drewcarlson.coingecko.CoinGeckoClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import java.util.*

class CoinMarketRepositoryImpl(
    private val coinMarketApi: CoinMarketApi,
    private val coinMarketDatabase: CoinMarketDatabase,
    okHttpClient: OkHttpClient
) : CoinMarketRepository {

    private val dollarApi = provideRetrofit(okHttpClient).create(DollarApi::class.java)

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

    override suspend fun getDollarValue(): DollarData {
        val jsonBody = JsonObject()
        jsonBody.addProperty("method", "spotRateHistory")
        val jsonData = JsonObject()
        jsonData.addProperty("base", "USD")
        jsonData.addProperty("base", "BRL")
        jsonData.addProperty("period", "week")
        jsonBody.addProperty("data", jsonData.toString())
        return dollarApi.getDolarValue(jsonBody).data
    }


    private fun checkIfAccountHasLimit(account: AccountDb): Boolean {
        return when {
            account.requestsLeft == 0 -> false
            account.creditLimitDaily == account.creditsUsedDay -> false
            account.creditsLeftMonth == 0 -> false
            else -> true
        }
    }

    override suspend fun getSmoothLovePotionValueLocal() =
        coinMarketDatabase.slpCoinDao().getSlpCoin()

    private suspend fun getAccountInfoLocal() = coinMarketDatabase.accountDao().getAccount()

}