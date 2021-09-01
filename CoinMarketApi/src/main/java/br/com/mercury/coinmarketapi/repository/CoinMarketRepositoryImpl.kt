package br.com.mercury.coinmarketapi.repository

import br.com.mercury.coinmarketapi.data.local.CoinMarketDatabase
import br.com.mercury.coinmarketapi.data.local.model.AccountDb
import br.com.mercury.coinmarketapi.model.AccountInfo
import br.com.mercury.coinmarketapi.network.CoinMarketApi
import java.util.*

open class CoinMarketRepositoryImpl(
    private val coinMarketApi: CoinMarketApi,
    private val coinMarketDatabase: CoinMarketDatabase
) :
    CoinMarketRepository {


    override suspend fun getAccountInfo(
        success: (account: AccountInfo) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        hasLimitOnAccount().let {
            if (it) {
                getInfoDatabase()
            } else {
                val coinValue = coinMarketApi.getSlpInfo().slpCoin.slpCoin
                coinValue.name
            }
        }
    }

    private suspend fun getInfoDatabase() {
    }

    private suspend fun hasLimitOnAccount(): Boolean {
        return try {
            val accountInfo = coinMarketApi.getAccountInfo().accountInfo
            val account = AccountDb(accountInfo)
            coinMarketDatabase.accountDao().createAccountEntity(account)
            when {
                account.requestsLeft == 0 -> true
                account.creditLimitDaily == account.creditsUsedDay -> true
                account.creditsLeftMonth == 0 -> true
                else -> false
            }

        } catch (ex: Exception) {
            false
        }
    }


}