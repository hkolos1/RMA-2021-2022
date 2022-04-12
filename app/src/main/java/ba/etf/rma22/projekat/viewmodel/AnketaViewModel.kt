package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository

class AnketaViewModel {

    fun getMyAnkete(): List<Anketa> {
        return sortirajPoDatumu(AnketaRepository.getMyAnkete())
    }

    fun getAll(): List<Anketa> {
        return sortirajPoDatumu(AnketaRepository.getAll())
    }

    fun getDone(): List<Anketa> {
        return sortirajPoDatumu(AnketaRepository.getDone())
    }

    fun getFuture(): List<Anketa> {
        return sortirajPoDatumu(AnketaRepository.getFuture())
    }

    fun getNotTaken(): List<Anketa> {
        return sortirajPoDatumu(AnketaRepository.getNotTaken())
    }

    /*Funkcija koja sortira ankete po datumu*/
    fun sortirajPoDatumu(lista : List<Anketa>) : List<Anketa>{
        return lista.sortedBy { it.datumPocetak }
    }
}