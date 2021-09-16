package br.com.mercury.coinmarketapi.data.network.model

import com.google.gson.annotations.SerializedName

data class DolarResponse(
    @SerializedName("data") val data: DollarData
)

data class DollarData(
    @SerializedName("CurrentInterbankRate") val currentInterbankRate: Double
)