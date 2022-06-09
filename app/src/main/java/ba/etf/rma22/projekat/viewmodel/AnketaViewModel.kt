package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AnketaViewModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun getAll(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> Unit, offset : Int = 0){
        scope.launch{
            val result = AnketaRepository.getAll(offset)
            when (result) {
                is List<Anketa> -> onSuccess?.invoke(result!!)
                else-> onError?.invoke()
            }
        }
    }

    fun getById( id : Int, onSuccess: (ankete: Anketa) -> Unit, onError: () -> Unit){
        scope.launch{
            val result = AnketaRepository.getById(id)
            when (result) {
                is Anketa -> onSuccess?.invoke(result!!)
                else-> onError?.invoke()
            }
        }
    }

    fun getUpisane(onSuccess: (ankete: List<Anketa>) -> Unit, onError: () -> Unit,
                   id: String = "2d5ceafb-0fd2-4282-b767-0a9740cd2c20"){
        scope.launch{
            val result = AnketaRepository.getUpisane()
            when (result) {
                is List<Anketa> -> onSuccess?.invoke(result!!)
                else-> onError?.invoke()
            }
        }
    }

    /*Funkcija koja sortira ankete po datumu*/
    fun sortirajPoDatumu(lista : List<Anketa>) : List<Anketa>{
        return lista.sortedBy { it.datumPocetak }
    }
}