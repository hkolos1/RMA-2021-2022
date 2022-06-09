package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PitanjeAnketaRepository {
    companion object{
        suspend fun getPitanja(idAnketa: Int): List<Pitanje>? {
            return withContext(Dispatchers.IO) {
                var response = ApiAdapter.retrofit.dajPitanja(idAnketa)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }
    }
}