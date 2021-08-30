package br.com.mercury.coinmarketapi.repository

import br.com.mercury.coinmarketapi.model.AccountInfo

interface CoinMarketRepository {
    suspend fun getAccountInfo(
        success: (account: AccountInfo) -> Unit,
        failure: (throwable: Throwable) -> Unit
    )
}