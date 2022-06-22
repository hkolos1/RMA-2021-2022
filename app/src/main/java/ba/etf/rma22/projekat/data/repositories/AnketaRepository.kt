package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.dao.AppDatabase
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnketaRepository {

    companion object {
        private lateinit var context: Context
        fun setContext(_context: Context) {
            context = _context
        }


        suspend fun getAll(offset: Int = 0): List<Anketa> {
            return withContext(Dispatchers.IO) {
                try {
                    var i = 1
                    var ankete = mutableListOf<Anketa>()
                    if (offset == 0) {
                        while (true) {
                            var response = ApiAdapter.retrofit.dajSveAnkete(i)
                            val responseBody = response.body()
                            if (responseBody != null) {
                                if (responseBody.isEmpty()) break
                                if (responseBody != null) {
                                    ankete.addAll(responseBody)
                                }
                            }
                            i++
                        }
                    }
                    try {
                        var baza = AppDatabase.getInstance(context)
                        baza.anketaDao().spasiSveAnkete(ankete)
                    } catch (err: Exception) {

                    }
                    return@withContext ankete

                } catch (err: Exception) {
                    var baza = AppDatabase.getInstance(context)
                    var anketica = baza.anketaDao().dajSveAnkete()
                    return@withContext anketica
                }

            }
        }

        suspend fun getById(id: Int): Anketa {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.dajAnketu(id)
                val responseBody = response.body()
                return@withContext responseBody!!
            }
        }

        suspend fun getUpisane(id: String = "2d5ceafb-0fd2-4282-b767-0a9740cd2c20"): List<Anketa> {
            return withContext(Dispatchers.IO) {
                var response1 = ApiAdapter.retrofit.dajStudentoveGrupe()
                val responseBody1 = response1.body()
                var ankete = mutableListOf<Anketa>()
                if (responseBody1 != null) {
                    for (a in responseBody1) {
                        var response = ApiAdapter.retrofit.dajUpisaneAnkete(a.id)
                        val responseBody = response.body()
                        if (responseBody != null) {
                            ankete.addAll(responseBody)
                        }
                    }
                }
                return@withContext ankete
            }
        }

        suspend fun getUpisaneGrupe(): List<Grupa>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.dajStudentoveGrupe()
                val responseBody = response.body()
                return@withContext responseBody
            }
        }
    }
}