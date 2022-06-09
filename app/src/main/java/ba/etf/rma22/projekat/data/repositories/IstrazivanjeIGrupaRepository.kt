package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IstrazivanjeIGrupaRepository {

    companion object {

        suspend fun getIstrazivanja(offset: Int = 0): List<Istrazivanje> {
            return withContext(Dispatchers.IO) {
                var i=1
                var istrazivanja =  mutableListOf<Istrazivanje>()
                if(offset == 0){
                    while (true){
                        var response = ApiAdapter.retrofit.dajSvaIstrazivanja(i)
                        val responseBody = response.body()
                        if (responseBody != null) {
                            if(responseBody.isEmpty())
                                break
                            if (responseBody != null) {
                                    istrazivanja.addAll(responseBody)
                            }
                        }
                        i++
                    }
                }
                return@withContext istrazivanja
            }
        }

        suspend fun getGrupe(): List<Grupa>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.dajSveGrupe()
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun upisiUGrupu(idGrupa: Int): Boolean? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.upisiStudentaUGrupu(idGrupa)
                val responseBody = response.body()
                if (responseBody != null) {
                    if(responseBody.message.contains("Account not found.", true) ||
                        responseBody.message.contains("Grupa not found."))
                        return@withContext false
                }
                return@withContext true
            }
        }

        suspend fun getUpisaneGrupe(): List<Grupa>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.dajStudentoveGrupe()
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun getGrupeZaIstrazivanje(idIstrazivanja:Int):List<Grupa> {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.dajGrupeZaIstrazivanje(idIstrazivanja)
                val responseBody = response.body()
                return@withContext responseBody!!
            }
        }
    }
}