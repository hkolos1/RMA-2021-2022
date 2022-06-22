package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Pitanje

@Dao
interface PitanjeDAO {

    @Query("DELETE FROM pitanje")
    suspend fun obrisiBazu()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun napraviBazu(pitanje: List<Pitanje>)

    @Query("SELECT * FROM pitanje")
    suspend fun dajSvaPitanjaUBazu():List<Pitanje>

    @Insert
    suspend fun spasiPitanja(pitanje: List<Pitanje>)
}