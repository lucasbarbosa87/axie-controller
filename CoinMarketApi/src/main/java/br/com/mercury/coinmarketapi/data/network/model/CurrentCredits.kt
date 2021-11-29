package br.com.mercury.coinmarketapi.data.network.model

import com.google.gson.annotations.SerializedName

data class CurrentCredits(
    @SerializedName("credits_left") val creditsLeft: Int,
    @SerializedName("credits_used") val creditsUsed: Int,
)