package br.com.mercury.coinmarketapi.model

import com.google.gson.annotations.SerializedName

data class AccountPlan(
    @SerializedName("credit_limit_daily") val creditLimitDaily: Int,
    @SerializedName("credit_limit_monthly") val creditLimitMonthly: Int,
    @SerializedName("rate_limit_minute") val rateLimitMinute: Int,
    @SerializedName("credit_limit_daily_reset_timestamp") val creditLimitResetDaily: String,
    @SerializedName("credit_limit_monthly_reset_timestamp") val creditLimitResetMonthly: String,
)