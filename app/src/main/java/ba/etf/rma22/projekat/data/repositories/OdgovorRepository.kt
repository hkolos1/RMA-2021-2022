package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.dao.AppDatabase
import ba.etf.rma22.projekat.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OdgovorRepository {
    companion object {
        private lateinit var context: Context
        fun setContext(_context: Context) {
            context = _context
        }
        suspend fun getOdgovoriAnketa(idAnkete: Int): List<Odgovor>? {
            return withContext(Dispatchers.IO) {
                var idAnketaTaken: Int = 0
                var responseTaken = ApiAdapter.retrofit.dajZapocete().body()
                if (responseTaken != null) {
                    for (taken in responseTaken) {
                        if (taken.AnketumId == idAnkete)
                            idAnketaTaken = taken.id
                    }
                }
                var response = ApiAdapter.retrofit.dajOdgovore(idAnketaTaken)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun postaviOdgovorAnketa(idAnketaTaken:Int,idPitanje:Int,odgovor:Int):Int{

            return withContext(Dispatchers.IO) {
                try {
                    var progres = 0
                    var a = TakeAnketaRepository.getPoceteAnkete()?.find { An -> An.id == idAnketaTaken }
                    var pitanja = a?.let { PitanjeAnketaRepository.getPitanja(it.AnketumId) }
                    if (pitanja != null) {
                        if (a != null) {
                            progres = zaokruziProgres(a.progres+(100/pitanja.size))

                        }
                    }
                    var body = OdgovorBody(odgovor,idPitanje,progres)
                    var response = ApiAdapter.retrofit.dodajOdgovor(AccountRepository.getHash(), idAnketaTaken, body)
                    try {
                        var baza = AppDatabase.getInstance(context)
                        baza.odgovorDao().spasiOdgovor(Odgovor(idPitanje,odgovor,idAnketaTaken))
                        return@withContext progres
                    }catch (err:Exception){

                    }
                    return@withContext progres;
                }catch (err:Exception){
                    return@withContext -1
                }

            }
        }
        private fun zaokruziProgres(pro:Int):Int{
            var a = pro%20
            var noviProgress : Int
            if(a==0)
                return pro
            if(a<10)
                noviProgress = pro-a
            else noviProgress = pro+20-a
            if(noviProgress>100)
                noviProgress=100
            return noviProgress
        }
    }
}