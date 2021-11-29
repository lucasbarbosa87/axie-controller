package br.com.mercury.coinmarketapi.data.network.model

import com.google.gson.annotations.SerializedName

data class CurrentMinute(
    @SerializedName("requests_left") val requestsLeft: Int,
    @SerializedName("requests_made") val requestsMade: Int,
)