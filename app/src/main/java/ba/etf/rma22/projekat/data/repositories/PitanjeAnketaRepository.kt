package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.staticdata.pitanja
import ba.etf.rma22.projekat.data.staticdata.pitanjeAnketa

class PitanjeAnketaRepository {
    companion object{
        fun getPitanja(nazivAnkete:String, nazivIstrazivanja:String):List<Pitanje>{

            var pitanjaZaAnketu = mutableListOf<Pitanje>()
            for(pk in pitanjeAnketa()){
                if(nazivAnkete==pk.anketa){
                    for(p in pitanja()){
                        if(p.naziv==pk.naziv){
                            pitanjaZaAnketu.add(p)
                        }
                    }
                }
            }
            return pitanjaZaAnketu
        }
    }
}