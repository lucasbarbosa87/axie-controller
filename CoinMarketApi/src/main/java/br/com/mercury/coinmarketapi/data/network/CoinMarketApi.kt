package br.com.mercury.coinmarketapi.data.network

import br.com.mercury.coinmarketapi.data.network.model.Coin
import br.com.mercury.coinmarketapi.data.network.model.Account
import br.com.mercury.coinmarketapi.utils.accountKey
import retrofit2.http.*

interface CoinMarketApi {

    @GET("key/info")
    @Headers(headerCoinMarket)
    suspend fun getAccountInfo(): Account

    @GET("cryptocurrency/quotes/latest")
    @Headers(headerCoinMarket)
    suspend fun getSlpInfo(
        @Query("symbol") symbol: String = "slp",
        @Query("convert") convert: String = "eth"
    ): Coin


    companion object {
        const val headerCoinMarket = "X-CMC_PRO_API_KEY: ${accountKey}"
    }
}