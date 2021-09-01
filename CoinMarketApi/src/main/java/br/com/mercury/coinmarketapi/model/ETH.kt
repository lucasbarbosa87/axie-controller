package br.com.mercury.coinmarketapi.model

import com.google.gson.annotations.SerializedName

data class ETH(
    @SerializedName("price") val price: Double,
    @SerializedName("last_updated") val lastUpdated: String
)