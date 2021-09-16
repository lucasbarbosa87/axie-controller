package br.com.mercury.coinmarketapi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.mercury.coinmarketapi.data.local.model.SlpCoinDb

@Dao
interface SlpCoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateSlpCoin(slpCoinDb: SlpCoinDb)


    @Query("select * from SlpCoin limit 1")
    suspend fun getSlpCoin(): SlpCoinDb
}