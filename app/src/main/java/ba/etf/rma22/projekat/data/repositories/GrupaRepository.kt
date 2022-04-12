package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.staticdata.grupe

class GrupaRepository {
    companion object {
        init {
        }
        fun getGroupsByIstrazivanje(nazivIstrazivanja: String): List<Grupa> {
            var lista = mutableListOf<Grupa>()
            for(G in grupe()){
                if(G.nazivIstrazivanja==nazivIstrazivanja){
                    lista.add(G)
                }
            }
            return lista
        }
    }
}