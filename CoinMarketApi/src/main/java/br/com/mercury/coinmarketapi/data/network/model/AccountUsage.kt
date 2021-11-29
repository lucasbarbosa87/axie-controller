package br.com.mercury.coinmarketapi.data.network.model

import com.google.gson.annotations.SerializedName

data class AccountUsage(
    @SerializedName("current_day") val currentDay: CurrentCredits,
    @SerializedName("current_minute") val currentMinute: CurrentMinute,
    @SerializedName("current_month") val currentMonth: CurrentCredits
)