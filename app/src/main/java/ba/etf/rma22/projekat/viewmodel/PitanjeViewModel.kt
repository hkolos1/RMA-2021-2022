package ba.etf.rma22.projekat.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PitanjeViewModel {
    val scope = CoroutineScope(
        Job() + Dispatchers.Main)

    var pitanja = MutableLiveData<List<Pitanje>>()
    fun dajPitanja(Context: Context, onSuccess: (pitanja: List<Pitanje>) -> Unit, onError: () -> Unit, idAnketa : Int) {
        scope.launch {
            PitanjeAnketaRepository.setContext(Context)
            PitanjeAnketaRepository.getPitanja(idAnketa)
            val result = PitanjeAnketaRepository.getPitanja(idAnketa)
            when (result) {
                is List<Pitanje> -> {
                    onSuccess?.invoke(result)
                    pitanja.postValue(result!!)
                }
                else -> onError?.invoke()
            }
        }
    }
}