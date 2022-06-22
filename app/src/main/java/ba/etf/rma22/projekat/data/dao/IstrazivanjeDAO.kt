package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje

@Dao
interface IstrazivanjeDAO {

    @Query("DELETE FROM istrazivanje")
    suspend fun obrisiBazu()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun napraviBazu(istrazivanja: List<Istrazivanje>)

    @Query("SELECT * FROM istrazivanje")
    suspend fun dajSvaIstrazivanja(): List<Istrazivanje>

    @Insert
    suspend fun spasiIstrazivanje(istrazivanje: List<Istrazivanje>)

}