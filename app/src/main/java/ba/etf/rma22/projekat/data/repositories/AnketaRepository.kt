package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.UserRepository.Companion.user
import ba.etf.rma22.projekat.data.staticdata.ankete
import java.util.*

class AnketaRepository {

    companion object {
        init {
        }
        fun getMyAnkete(): List<Anketa> {
            /* --- Zbog testa koji zatvara aplikaciju i testira,
             nisam uspio spasiti podatke --- */

           /*
            var lista = mutableListOf<Anketa>()
            var brojac = 0
            while(brojac < user.istrazivanja.size){
                for(U in getAll()){
                   if(U.nazivIstrazivanja== user.istrazivanja[brojac] &&
                      U.nazivGrupe == user.grupe[brojac])
                       {
                       lista.add(U)
                   }
               }
                brojac++
           }
            return lista
            */

            return ankete()
        }


        fun getAll(): List<Anketa> {
            return ankete();
        }

        fun getDone(): List<Anketa> {
            var lista = mutableListOf<Anketa>()
            for(K in getMyAnkete()){
                if(K.datumRada!=null){
                    lista.add(K)
                }
            }
            return lista
        }

        fun getFuture(): List<Anketa> {
            var lista = mutableListOf<Anketa>()
            for(K in getMyAnkete()){
                if(K.datumRada==null && K.datumPocetak.after(Calendar.getInstance().time)){
                    lista.add(K)
                }
            }
            return lista
        }

        fun getNotTaken(): List<Anketa> {
            var lista = mutableListOf<Anketa>()
            for(K in getMyAnkete()){
                if(K.datumRada==null && K.datumPocetak.before(Calendar.getInstance().time) &&
                    K.datumKraj.before(Calendar.getInstance().time)){
                    lista.add(K)
                }
            }
            return lista
        }
    }
}