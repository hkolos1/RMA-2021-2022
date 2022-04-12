package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository

class IstrazivanjeViewModel {
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