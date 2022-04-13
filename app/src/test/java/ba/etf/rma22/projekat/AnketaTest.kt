package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.staticdata.ankete
import ba.etf.rma22.projekat.data.staticdata.datum
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import junit.framework.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class AnketaTest {

    @Test
    fun GetAllAnkete() {
        val anketa = AnketaRepository.getAll()
        assertEquals(anketa.size, ankete().size)
    }

    @Test
    fun GetMyAnkete() {
        val anketa = AnketaRepository.getMyAnkete()
        assertEquals(anketa.size, 2)
    }

    @Test
    fun GetDoneAnkete() {
        val anketa = AnketaRepository.getDone()
        assertEquals(anketa.size, 1)
    }

    @Test
    fun GetFutureAnkete() {
        val anketa = AnketaRepository.getFuture()
        assertEquals(anketa.size, 0)
    }

    @Test
    fun GetNotTakenAnkete() {
        val anketa = AnketaRepository.getNotTaken()
        assertEquals(anketa.size, 0)
    }

    @Test
    fun TestSortirajPoDatumu() {
        var model : AnketaViewModel = AnketaViewModel()
        var lista : List<Anketa> = listOf<Anketa>(
            Anketa("Anketa1", "Istrazivanje broj 1", datum(10,2,2022), datum(10,5,2022), datum(10,4,2022), 5, "Grupa1", 0.5f),
            Anketa("Anketa2", "Istrazivanje broj 2", datum(9,3,2022), datum(10,5,2022), datum(10,4,2022), 5, "Grupa1", 0.5f),
            Anketa("Anketa3", "Istrazivanje broj 3", datum(10,4,2022), datum(10,5,2022), datum(10,4,2022), 5, "Grupa1", 0.5f)
        )

        var listCheck : List<Anketa> = listOf(
            Anketa("Anketa1", "Istrazivanje broj 1", datum(10,2,2022), datum(10,5,2022), datum(10,3,2022), 5, "Grupa1", 0.5f),
            Anketa("Anketa2", "Istrazivanje broj 2", datum(9,3,2022), datum(10,5,2022), datum(9,3,2022), 5, "Grupa1", 0.5f),
            Anketa("Anketa3", "Istrazivanje broj 3", datum(10,4,2022), datum(10,5,2022), datum(10,4,2022), 5, "Grupa1", 0.5f)
        )
        lista=model.sortirajPoDatumu(lista)
        var tvrdnjaTrue : Boolean = false
        for (i in 0.. lista.size-1){
            if(!(lista[i].equals(listCheck[i]))){
                tvrdnjaTrue=true
            }
        }
        assert(tvrdnjaTrue)
    }
}