package br.com.mercury.coinmarketapi.repository

import br.com.mercury.coinmarketapi.data.local.model.AccountDb
import br.com.mercury.coinmarketapi.data.local.model.SlpCoinDb
import br.com.mercury.coinmarketapi.model.AccountInfo

interface CoinMarketRepository {
    suspend fun getAccountInfoNetwork()
    suspend fun getSmoothLovePotionValueNetwork()
    suspend fun overRideInformation()
    suspend fun getSmoothLovePotionValueLocal(): SlpCoinDb
}