package br.com.mercury.coinmarketapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.mercury.coinmarketapi.data.local.dao.AccountDao
import br.com.mercury.coinmarketapi.data.local.model.AccountDb

@Database(entities = [AccountDb::class], version = 1, exportSchema = false)
abstract class CoinMarketDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao

}