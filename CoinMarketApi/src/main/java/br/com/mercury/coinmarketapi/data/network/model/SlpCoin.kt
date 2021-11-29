package br.com.mercury.coinmarketapi.data.network.model

import com.google.gson.annotations.SerializedName

data class SlpCoin(
    @SerializedName("name") val name: String,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("is_active") val isActive: Int,
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("quote") val quote: QuoteData
)

data class QuoteData(
    @SerializedName("ETH") val quote: ETH
)