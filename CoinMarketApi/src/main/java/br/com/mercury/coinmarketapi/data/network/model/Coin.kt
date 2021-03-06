package br.com.mercury.coinmarketapi.data.network.model

import com.google.gson.annotations.SerializedName

data class Coin(
    @SerializedName("data") val slpCoin: SlpData
)

data class SlpData(
    @SerializedName("SLP") val slpCoin: SlpCoin
)
