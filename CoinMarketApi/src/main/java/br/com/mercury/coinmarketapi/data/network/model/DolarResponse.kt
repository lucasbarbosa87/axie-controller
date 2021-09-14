package br.com.mercury.coinmarketapi.data.network.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class DolarResponse(
    @SerializedName("data") val data: DollarData
)

data class DollarData(
    @SerializedName("CurrentInterbankRate") val CurrentInterbankRate: BigDecimal
)