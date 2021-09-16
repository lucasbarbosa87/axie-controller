package br.com.mercury.coinmarketapi.repository

import br.com.mercury.coinmarketapi.data.local.model.SlpCoinDb

interface CoinMarketRepository {
    suspend fun getAccountInfoNetwork()
    suspend fun getSmoothLovePotionValueNetwork()
    suspend fun overRideInformation()
    suspend fun getSmoothLovePotionValueLocal(): SlpCoinDb
    suspend fun getDollarValue(): Double
    suspend fun getDollarValueNetwork()
}