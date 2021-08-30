package br.com.mercury.coinmarketapi.repository

import br.com.mercury.coinmarketapi.model.AccountInfo
import br.com.mercury.coinmarketapi.network.CoinMarketApi

open class CoinMarketRepositoryImpl(private val coinMarketApi: CoinMarketApi) :
    CoinMarketRepository {


    override suspend fun getAccountInfo(
        success: (account: AccountInfo) -> Unit,
        failure: (throwable: Throwable) -> Unit
    ) {
        try {
            success(coinMarketApi.getAccountInfo().accountInfo)
        } catch (ex: Exception) {
            failure(ex)
        }
    }


}