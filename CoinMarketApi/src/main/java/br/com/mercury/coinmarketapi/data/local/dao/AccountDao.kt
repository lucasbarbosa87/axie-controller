package br.com.mercury.coinmarketapi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.mercury.coinmarketapi.data.local.model.AccountDb

@Dao
interface AccountDao {

    @Query("select * from account limit 1")
    suspend fun getAccount(): AccountDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAccountEntity(accountDb: AccountDb)
}