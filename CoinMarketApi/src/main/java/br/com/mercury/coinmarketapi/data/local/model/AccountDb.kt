package br.com.mercury.coinmarketapi.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.mercury.coinmarketapi.model.AccountInfo
import java.util.*

@Entity(tableName = "Account")
data class AccountDb(
    @PrimaryKey @ColumnInfo(name = "id") val accountId: String,
    @ColumnInfo(name = "creditLimitDaily") val creditLimitDaily: Int,
    @ColumnInfo(name = "creditLimitMonthly") val creditLimitMonthly: Int,
    @ColumnInfo(name = "rateLimitMinute") val rateLimitMinute: Int,
    @ColumnInfo(name = "creditLimitResetDaily") val creditLimitResetDaily: String,
    @ColumnInfo(name = "creditLimitResetMonthly") val creditLimitResetMonthly: String,
    @ColumnInfo(name = "requestsLeft") val requestsLeft: Int,
    @ColumnInfo(name = "requestsMade") val requestsMade: Int,
    @ColumnInfo(name = "creditsLeftDay") val creditsLeftDay: Int,
    @ColumnInfo(name = "creditsUsedDay") val creditsUsedDay: Int,
    @ColumnInfo(name = "creditsLeftMonth") val creditsLeftMonth: Int,
    @ColumnInfo(name = "creditsUsedMonth") val creditsUsedMonth: Int
) {
    constructor(accountInfo: AccountInfo) : this(
        accountId = UUID.randomUUID().toString(),
        creditLimitDaily = accountInfo.plan.creditLimitDaily,
        creditLimitMonthly = accountInfo.plan.creditLimitMonthly,
        creditLimitResetDaily = accountInfo.plan.creditLimitResetDaily,
        creditLimitResetMonthly = accountInfo.plan.creditLimitResetMonthly,
        creditsLeftDay = accountInfo.usage.currentDay.creditsLeft,
        creditsLeftMonth = accountInfo.usage.currentMonth.creditsLeft,
        creditsUsedDay = accountInfo.usage.currentDay.creditsUsed,
        creditsUsedMonth = accountInfo.usage.currentMonth.creditsUsed,
        requestsLeft = accountInfo.usage.currentMinute.requestsLeft,
        requestsMade = accountInfo.usage.currentMinute.requestsMade,
        rateLimitMinute = accountInfo.plan.rateLimitMinute
    )
}