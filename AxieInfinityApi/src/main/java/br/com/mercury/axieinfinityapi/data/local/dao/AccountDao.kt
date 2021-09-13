package br.com.mercury.axieinfinityapi.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.mercury.axieinfinityapi.data.local.model.AxieAccountDb

@Dao
interface AccountDao {
    @Query("select * from account limit 1")
    suspend fun getAccount(): AxieAccountDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAccount(axieAccountDb: AxieAccountDb)
}