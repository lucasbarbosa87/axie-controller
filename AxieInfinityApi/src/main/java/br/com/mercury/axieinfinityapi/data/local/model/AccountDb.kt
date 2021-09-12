package br.com.mercury.axieinfinityapi.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.mercury.axieinfinityapi.data.network.AxieProfileBriefResponse

@Entity(tableName = "Account")
data class AccountDb(
    @PrimaryKey @ColumnInfo(name = "id") val accountId: String,
    @ColumnInfo(name = "roninAdress") val roninAdress: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "name") val name: String
) {
    constructor(accountInfo: AxieProfileBriefResponse) : this(
        accountId = accountInfo.profile!!.accountId,
        roninAdress = accountInfo.profile.adress.ronin,
        email = accountInfo.profile.email,
        name = accountInfo.profile.name
    )
}