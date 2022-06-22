package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.dao.AppDatabase
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PitanjeAnketaRepository {
    companion object{
        private lateinit var context: Context
        fun setContext(_context: Context) {
            context = _context
        }
        suspend fun getPitanja(idAnketa: Int): List<Pitanje>? {
            return withContext(Dispatchers.IO) {

                try {
                    var response = ApiAdapter.retrofit.dajPitanja(idAnketa)
                    val responseBody = response.body()
                    try{
                        var baza = AppDatabase.getInstance(context)
                        baza.pitanjeDao().spasiPitanja(responseBody!!)
                        return@withContext responseBody
                    }catch (err:Exception){
                        return@withContext responseBody!!
                    }
                } catch (err:Exception){
                    try {
                        var baza = AppDatabase.getInstance(context)
                        var pitanje = baza.pitanjeDao().dajSvaPitanjaUBazu()
                        return@withContext pitanje
                    }catch (err:Exception) {
                        return@withContext null!!
                    }
                }
            }
        }
    }
}