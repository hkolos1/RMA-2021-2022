package ba.etf.rma22.projekat.viewmodel

import androidx.lifecycle.MutableLiveData
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.repositories.TakeAnketaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AnketaTakenViewModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    var ankete = MutableLiveData<List<AnketaTaken>?>()
    var zapoceteAnkete = MutableLiveData<AnketaTaken>()
    //var surveys = MutableLiveData<List<Anketa>>()

    fun zapocniAnketu(onSuccess: (anketaTaken: AnketaTaken) -> Unit, onError: () -> Unit, idAnketa : Int){
        scope.launch{
            val result = TakeAnketaRepository.zapocniAnketu(idAnketa)
            when (result) {
                is AnketaTaken -> {
                    onSuccess?.invoke(result)
                    zapoceteAnkete.postValue(result!!)
                }
                else-> onError?.invoke()
            }
        }
    }
}