package br.com.mercury.coinmarketapi.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.mercury.coinmarketapi.model.SlpCoin

@Entity(tableName = "SlpCoin")
data class SlpCoinDb(
    @ColumnInfo(name = "name") val name: String,
    @PrimaryKey @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "isActive") val isActive: Int,
    @ColumnInfo(name = "lastUpdated") val lastUpdated: String,
    @ColumnInfo(name = "price") val price: Double
) {
    constructor(slpCoin: SlpCoin) : this(
        name = slpCoin.name,
        symbol = slpCoin.symbol,
        isActive = slpCoin.isActive,
        lastUpdated = slpCoin.lastUpdated,
        price = slpCoin.quote.quote.price
    )
}