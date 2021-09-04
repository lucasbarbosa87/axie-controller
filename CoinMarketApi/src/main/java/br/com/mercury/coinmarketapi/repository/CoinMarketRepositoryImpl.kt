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

open class CoinMarketRepositoryImpl(
    private val coinMarketApi: CoinMarketApi,
    private val coinMarketDatabase: CoinMarketDatabase
) : CoinMarketRepository {
    override suspend fun getSlpValue(
        success: (account: SlpCoinDb) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        try {
            getAccountInfoNetwork().let {
                getInfoDatabase().let {
                    if (it) {
                        success(getCoinDatabase())
                    } else {
                        success(getCoinNetwork())
                    }
                }
            }
        } catch (ex: Exception) {
            failure(ex)
        }
    }


    override suspend fun getAccountInfo(
        success: (account: AccountInfo) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        getAccountInfoNetwork().let {
            getInfoDatabase().let {
                if (it) {
                    getCoinDatabase()
                } else {
                    getCoinNetwork()
                }
            }
        }
    }

    private suspend fun getCoinNetwork(): SlpCoinDb {
        val coinValue = coinMarketApi.getSlpInfo().slpCoin.slpCoin
        val coinDb = SlpCoinDb(coinValue)
        coinMarketDatabase.slpCoinDao().insertOrUpdateSlpCoin(coinDb)
        return coinDb
    }

    private suspend fun getCoinDatabase() = coinMarketDatabase.slpCoinDao().getSlpCoin()


    private suspend fun getInfoDatabase(): Boolean {
        val account = coinMarketDatabase.accountDao().getAccount()
        return when {
            account.requestsLeft == 0 -> true
            account.creditLimitDaily == account.creditsUsedDay -> true
            account.creditsLeftMonth == 0 -> true
            else -> false
        }
    }

    private suspend fun getAccountInfoNetwork() {
        val accountInfo = coinMarketApi.getAccountInfo().accountInfo
        val account = AccountDb(accountInfo)
        coinMarketDatabase.accountDao().createAccountEntity(account)

    }

}