package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Grupa

@Dao
interface GrupaDAO {

    @Query("DELETE FROM grupa")
    suspend fun obrisiBazu()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun napraviBazu(grupe: List<Grupa>)

    @Query("SELECT * FROM grupa")
    suspend fun dajSveGrupeuBazu():List<Grupa>

    @Insert
    suspend fun spasiGrupe(grupe: List<Grupa>)

    @Query("SELECT * FROM grupa WHERE IstrazivanjeId =:IstrazivanjeId")
    suspend fun dajGrupeZaIstrazivanje(IstrazivanjeId: Int): List<Grupa>
}