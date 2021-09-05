package br.com.mercury.coinmarketapi.repository

import br.com.mercury.coinmarketapi.data.local.CoinMarketDatabase
import br.com.mercury.coinmarketapi.data.local.model.AccountDb
import br.com.mercury.coinmarketapi.data.local.model.SlpCoinDb
import br.com.mercury.coinmarketapi.model.AccountInfo
import br.com.mercury.coinmarketapi.network.CoinMarketApi
import drewcarlson.coingecko.CoinGeckoClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class CoinMarketRepositoryImpl(
    private val coinMarketApi: CoinMarketApi,
    private val coinMarketDatabase: CoinMarketDatabase
) : CoinMarketRepository {

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


    private fun checkIfAccountHasLimit(account: AccountDb): Boolean {
        return when {
            account.requestsLeft == 0 -> false
            account.creditLimitDaily == account.creditsUsedDay -> false
            account.creditsLeftMonth == 0 -> false
            else -> true
        }
    }

    private suspend fun getSmoothLovePotionValueLocal() =
        coinMarketDatabase.slpCoinDao().getSlpCoin()

    private suspend fun getAccountInfoLocal() = coinMarketDatabase.accountDao().getAccount()

}