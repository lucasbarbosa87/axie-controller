package br.com.mercury.axieinfinityapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.mercury.axieinfinityapi.data.local.dao.AccountDao
import br.com.mercury.axieinfinityapi.data.local.model.AxieAccountDb

@Database(entities = [AxieAccountDb::class], version = 1, exportSchema = false)
abstract class AxieDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
}