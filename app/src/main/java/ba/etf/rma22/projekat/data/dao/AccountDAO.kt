package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Account

@Dao
interface AccountDAO {

    @Query("SELECT * FROM account")
    suspend fun dajAccount(): Account

    @Query("DELETE FROM account")
    suspend fun deleteAccount()

    @Insert
    suspend fun ubaciAccount(vararg acc: Account)
}