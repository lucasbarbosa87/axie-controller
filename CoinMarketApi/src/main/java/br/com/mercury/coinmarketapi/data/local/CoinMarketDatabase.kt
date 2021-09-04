package br.com.mercury.coinmarketapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.mercury.coinmarketapi.data.local.dao.AccountDao
import br.com.mercury.coinmarketapi.data.local.dao.SlpCoinDao
import br.com.mercury.coinmarketapi.data.local.model.AccountDb
import br.com.mercury.coinmarketapi.data.local.model.SlpCoinDb

@Database(entities = [AccountDb::class, SlpCoinDb::class], version = 1, exportSchema = false)
abstract class CoinMarketDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun slpCoinDao(): SlpCoinDao
}