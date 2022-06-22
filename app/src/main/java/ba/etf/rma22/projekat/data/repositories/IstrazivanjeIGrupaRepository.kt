package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.dao.AppDatabase
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IstrazivanjeIGrupaRepository {

    companion object {
        private lateinit var context: Context
        fun setContext(_context: Context){
            context=_context
        }
        suspend fun getIstrazivanja(): List<Istrazivanje> {
            return withContext(Dispatchers.IO) {
                try {
                    var i=1
                    var offset = 0
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
                    try {
                        var baza = AppDatabase.getInstance(context)
                        baza.istrazivanjeDao().spasiIstrazivanje(istrazivanja)
                    }catch (err:Exception){

                    }
                    return@withContext istrazivanja
                }catch (err:Exception){
                    try {
                        var baza = AppDatabase.getInstance(context)
                        var rez = baza.istrazivanjeDao().dajSvaIstrazivanja()
                        return@withContext rez
                    }catch (err:Exception){

                    }
                    return@withContext null!!
                }
            }
        }

        suspend fun getIstrazivanja(offset: Int): List<Istrazivanje> {
            return withContext(Dispatchers.IO) {
                try{
                    var i=1
                        var response = ApiAdapter.retrofit.dajSvaIstrazivanja(offset)
                        var istrazivanjaDva =  mutableListOf<Istrazivanje>()
                        response.body()?.let { istrazivanjaDva.addAll(it) }
                        try {
                            var baza = AppDatabase.getInstance(context)
                            baza.istrazivanjeDao().spasiIstrazivanje(istrazivanjaDva)
                        }catch (err:Exception){

                        }
                        return@withContext istrazivanjaDva
                }catch (err:Exception){
                    try {
                        var baza = AppDatabase.getInstance(context)
                        var rez = baza.istrazivanjeDao().dajSvaIstrazivanja()
                        return@withContext rez
                    }catch (err:Exception){

                    }
                    return@withContext null!!
                }
            }
        }

        suspend fun getGrupe(): List<Grupa>? {
            return withContext(Dispatchers.IO) {

                try {
                    var response = ApiAdapter.retrofit.dajSveGrupe()
                    val responseBody = response.body()
                    try{
                        var baza = AppDatabase.getInstance(context)
                        baza.grupaDao().spasiGrupe(responseBody!!)
                        return@withContext responseBody
                    }catch (err:Exception){
                        return@withContext responseBody
                    }
                } catch (err:Exception){
                    try {
                        var baza = AppDatabase.getInstance(context)
                        var grupe = baza.grupaDao().dajSveGrupeuBazu()
                        return@withContext grupe
                    }catch (err:Exception) {
                        return@withContext null
                    }
                }
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
                try{
                    var response = ApiAdapter.retrofit.dajGrupeZaIstrazivanje(idIstrazivanja)
                    val responseBody = response.body()
                    return@withContext responseBody!!

                }catch (err:Exception){
                    try {
                        var baza = AppDatabase.getInstance(context)
                        var grupeIstrage = baza.grupaDao().dajGrupeZaIstrazivanje(idIstrazivanja)
                        return@withContext grupeIstrage
                    }catch (err: Exception){

                    }
                    return@withContext null!!
                }
            }
        }
    }
}