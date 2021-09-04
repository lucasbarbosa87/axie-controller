package br.com.mercury.coinmarketapi.repository

import br.com.mercury.coinmarketapi.data.local.model.SlpCoinDb
import br.com.mercury.coinmarketapi.model.AccountInfo

interface CoinMarketRepository {

    suspend fun getSlpValue(
        success: (account: SlpCoinDb) -> Unit,
        failure: (throwable: Throwable) -> Unit
    )

    suspend fun getAccountInfo(
        success: (account: AccountInfo) -> Unit,
        failure: (throwable: Throwable) -> Unit
    )
}