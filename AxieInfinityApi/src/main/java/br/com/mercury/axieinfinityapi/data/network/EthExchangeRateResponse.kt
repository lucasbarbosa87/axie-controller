package br.com.mercury.axieinfinityapi.data.network

import com.google.gson.annotations.SerializedName

data class EthExchangeRateResponse(
    @SerializedName("exchangeRate") val exchangeRate: ExchangeRate
)

data class ExchangeRate(
    @SerializedName("eth") val eth: EthData
)

data class EthData(
    @SerializedName("usd") val usd: Double
)