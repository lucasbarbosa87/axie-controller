package br.com.mercury.coinmarketapi.network

import br.com.mercury.coinmarketapi.model.Account
import br.com.mercury.coinmarketapi.model.AccountInfo
import br.com.mercury.coinmarketapi.model.SlpCoin
import retrofit2.http.*

interface CoinMarketApi {

    @GET("key/info")
    @Headers(headerCoinMarket)
    suspend fun getAccountInfo(): Account

    @GET("cryptocurrency/quotes/latest")
    suspend fun getSlpInfo(
        @Query("symbol") symbol: String = "slp",
        @Query("convert") convert: String = "eth"
    ): SlpCoin


    companion object {
        const val headerCoinMarket = "X-CMC_PRO_API_KEY: 1da4fe19-dee3-49e5-b5ac-5a0913b68fce"
    }
}