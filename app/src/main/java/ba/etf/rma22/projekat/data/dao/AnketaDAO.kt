package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Istrazivanje

@Dao
interface AnketaDAO {

    @Query("DELETE FROM anketa")
    suspend fun obrisiBazu()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun napraviBazu(ankete: List<Anketa>)

    @Query("SELECT * FROM anketa")
    suspend fun dajSveAnkete(): List<Anketa>

    @Insert
    suspend fun spasiSveAnkete(ankete: List<Anketa>)

}