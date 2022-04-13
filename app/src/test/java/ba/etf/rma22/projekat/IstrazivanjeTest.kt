package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository
import ba.etf.rma22.projekat.data.staticdata.istrazivanja
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.beans.HasProperty.hasProperty
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class IstrazivanjeTest {

    @Test
    fun TestGetUpisani() {
        val upisani=IstrazivanjeRepository.getUpisani()
        assertEquals(upisani.size, 2)
    }

    @Test
    fun TestGetAllIstrazivanje() {
        val istrazivanje = IstrazivanjeRepository.getAll()
        assertEquals(istrazivanje.size, 20)
    }

    @Test
    fun TestGetIstrazivanjeByGodina() {
        val istrazivanje = IstrazivanjeRepository.getIstrazivanjaByGodina(4)
        assertEquals(istrazivanje.size, 4)
        assertThat(istrazivanje, hasItem<Istrazivanje>(hasProperty("naziv")))

    }
    @Test
    fun TestIstrazivanjeViewModel() {
        val model : IstrazivanjeViewModel = IstrazivanjeViewModel()
        val lista : List<Istrazivanje> = istrazivanja().filter { it.godina == 2}
        model.getIstrazivanjaByGodina(2)
        assertTrue(model.getIstrazivanjaByGodina(2).size==lista.size &&
                model.getIstrazivanjaByGodina(2).containsAll(lista)  &&
                lista.containsAll(model.getIstrazivanjaByGodina(2)))
    }
}