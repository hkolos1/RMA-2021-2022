package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.models.AnketaTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

class TakeAnketaRepository {
    companion object {
        private lateinit var context: Context
        fun setContext(_context: Context) {
            context = _context
        }
        suspend fun zapocniAnketu(idAnketa: Int): AnketaTaken? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.zapocniAnketu(idAnketa)
                val responseBody = response.body()
                var anketaTaken = responseBody?.id?.let {
                    responseBody.datumRada?.let { it1 ->
                        AnketaTaken(
                            it, responseBody.student,
                            it1, responseBody.progres.roundToInt(), idAnketa
                        )
                    }
                }
                return@withContext anketaTaken
            }
        }

        suspend fun getPoceteAnkete(): List<AnketaTaken>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.dajZapocete()
                val responseBody = response.body()
                if(responseBody?.isEmpty()!!)
                    return@withContext null
                else return@withContext responseBody
            }
        }
    }
}