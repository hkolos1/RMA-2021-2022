package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import ba.etf.rma22.projekat.data.staticdata.grupe
import ba.etf.rma22.projekat.viewmodel.GrupaViewModel
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GrupaTest {
    @Test
    fun TestGetGroupsByIstrazivanje() {
        val grupe = GrupaRepository.getGroupsByIstrazivanje("Istrazivanje broj 1")
        junit.framework.Assert.assertEquals(grupe.size, 0)
    }
    @Test
    fun TestGrupaViewModel() {
        val model : GrupaViewModel = GrupaViewModel()
        val lista : List<Grupa> = grupe().filter { it.nazivIstrazivanja=="Istrazivanje broj 5" }
        assertTrue(model.getGroupsByIstrazivanje("Istrazivanje broj 5").size==lista.size &&
                lista.containsAll(model.getGroupsByIstrazivanje("Istrazivanje broj 5")) &&
                model.getGroupsByIstrazivanje("Istrazivanje broj 5").containsAll(lista))
    }
}