package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.repositories.UserRepository

class UserViewModel {
    fun dodajUpisanoIstrazivanje(godina: String, istrazivanje: String, grupa: String){
        UserRepository.user.trenutnaGodina = godina.toInt()
        UserRepository.user.istrazivanja.add(istrazivanje)
        UserRepository.user.grupe.add(grupa)
    }
}