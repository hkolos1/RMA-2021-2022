package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.staticdata.istrazivanja

class IstrazivanjeRepository {
    companion object {
        fun getUpisani(): List<Istrazivanje> {
            var userIstrazivanja : List<String> = UserRepository.user.istrazivanja
            var listaIstrazivanja = mutableListOf<Istrazivanje>()
            for(I in istrazivanja()){
                for(UI in userIstrazivanja){
                    if(UI==I.naziv){
                        listaIstrazivanja.add(I)
                    }
                }
            }
            return listaIstrazivanja
        }

        fun getAll(): List<Istrazivanje> {
            return istrazivanja()
        }

        fun getIstrazivanjaByGodina(godina:Int) : List<Istrazivanje>{
            var lista = mutableListOf<Istrazivanje>()
            for(I in istrazivanja()){
                if(I.godina==godina){
                    lista.add(I)
                }
            }
            return lista
        }
    }

}