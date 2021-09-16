package br.com.mercury.coinmarketapi.data.preferences

interface CoinMarketPreferences {
    fun getDollarValue(): Double
    suspend fun saveDollarValue(value: Double)
}