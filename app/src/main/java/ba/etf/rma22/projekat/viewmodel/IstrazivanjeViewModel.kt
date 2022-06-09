package ba.etf.rma22.projekat.viewmodel

import androidx.lifecycle.MutableLiveData
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository
import kotlinx.coroutines.*

class IstrazivanjeViewModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)
    var grupeZaIstrazivanje = MutableLiveData<List<Grupa>?>()


    fun getIstrazivanja(offset : Int, onSuccess: (ankete: List<Istrazivanje>) -> Unit, onError: () -> Unit){
        scope.launch{
            val result = IstrazivanjeIGrupaRepository.getIstrazivanja(offset)
            when (result) {
                is List<Istrazivanje> -> onSuccess?.invoke(result!!)
                else-> onError?.invoke()
            }
        }
    }

    fun getGrupe(onSuccess: (ankete: List<Grupa>) -> Unit, onError: () -> Unit){
        scope.launch{
            val result = IstrazivanjeIGrupaRepository.getGrupe()
            when (result) {
                is List<Grupa> -> onSuccess?.invoke(result!!)
                else-> onError?.invoke()
            }
        }
    }

    fun getGrupeZaIstrazivanje(
        onSuccess: (grupe: List<Grupa>) -> Unit, onError: () -> Unit, idIstrazivanja: Int) {
        scope.launch {
            val result = IstrazivanjeIGrupaRepository.getGrupeZaIstrazivanje(idIstrazivanja)
            when (result) {
                is List<Grupa> -> {
                    onSuccess?.invoke(result)
                    grupeZaIstrazivanje.postValue(result)
                }
                else -> onError?.invoke()
            }
        }
    }

    fun upisiStudenta(onSuccess: (uspjesno: Boolean) -> Unit, onError: () -> Unit, idGrupe: Int) {
        scope.launch {
            val result = IstrazivanjeIGrupaRepository.upisiUGrupu(idGrupe)
            when (result) {
                is Boolean -> onSuccess?.invoke(result)
                else -> onError?.invoke()
            }
        }
    }

    fun getUpisaneGrups(onSuccess: (grupe: List<Grupa>) -> Unit, onError: () -> Unit) {
        scope.launch {
            val result = IstrazivanjeIGrupaRepository.getUpisaneGrupe()
            when (result) {
                is List<Grupa> -> onSuccess?.invoke(result)
                else -> onError?.invoke()
            }
        }
    }

    fun getUpisani(): List<Istrazivanje> {
        return IstrazivanjeRepository.getUpisani()
    }

    fun getAll(): List<Istrazivanje> {
        return IstrazivanjeRepository.getAll()
    }

    fun getIstrazivanjaByGodina(godina:Int) : List<Istrazivanje>{
        return IstrazivanjeRepository.getIstrazivanjaByGodina(godina)
    }

}