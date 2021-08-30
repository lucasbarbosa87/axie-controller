package br.com.mercury.coinmarketapi.model

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("data") val accountInfo: AccountInfo
)

data class AccountInfo(
    @SerializedName("plan") val plan: AccountPlan,
    @SerializedName("usage") val usage: AccountUsage,
)