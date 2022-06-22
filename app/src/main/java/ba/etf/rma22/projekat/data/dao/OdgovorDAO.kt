package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.Pitanje

@Dao
interface OdgovorDAO {

    @Query("DELETE FROM odgovor")
    suspend fun obrisiBazu()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun napraviBazu(odgovori: List<Odgovor>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun dodajOdgovor(odgovor: Odgovor)

    @Insert
    suspend fun spasiOdgovor(odgovor: Odgovor)

}