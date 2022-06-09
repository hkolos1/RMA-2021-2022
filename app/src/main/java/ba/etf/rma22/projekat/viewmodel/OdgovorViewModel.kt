package ba.etf.rma22.projekat.viewmodel

import androidx.lifecycle.MutableLiveData
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.repositories.OdgovorRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OdgovorViewModel {
    val scope = CoroutineScope(
        Job() + Dispatchers.Main)
    var odgovori = MutableLiveData<List<Odgovor>>()
    var progres = MutableLiveData<Int>()
    fun addOdgovor(onSuccess: (progres: Int) -> Unit, onError: () -> Unit, idAnketaTaken: Int, idPitanje: Int, odgovor: Int) {
        scope.launch {
            val result = OdgovorRepository.postaviOdgovorAnketa(idAnketaTaken, idPitanje, odgovor)
            when (result) {
                is Int -> {
                    onSuccess?.invoke(result)
                    progres.postValue(result!!)
                }
                else -> onError?.invoke()
            }
        }
    }

    fun getOdgovori(onSuccess: (List<Odgovor>) -> Unit, onError: () -> Unit, idAnkete: Int) {
        scope.launch {
            val result = OdgovorRepository.getOdgovoriAnketa(idAnkete)
            when (result) {
                is List<Odgovor> -> {
                    onSuccess?.invoke(result)
                    odgovori.postValue(result!!)
                }
                else -> onError?.invoke()
            }
        }
    }
}