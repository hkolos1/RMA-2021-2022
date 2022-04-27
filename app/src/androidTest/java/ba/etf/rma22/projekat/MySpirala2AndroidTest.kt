package ba.etf.rma22.projekat

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.CoreMatchers.`is` as Is


@RunWith(AndroidJUnit4::class)
class MySpirala2AndroidTest {

    @get:Rule
    val intentsTestRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testPrviZadatak() {
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(1))
        var listaOdabira = listOf<String>("1", "2", "3", "4", "5")
        for (odabir in listaOdabira) {
            onView(withId(R.id.odabirGodina)).perform(click())
            onData(allOf(Is(instanceOf(String::class.java)), Is(odabir))).perform(click())
        }
        var listaIstraga = listOf<String>("Istraživanje","Istraživanje broj 8","Istraživanje broj 3",)
        for (odabir in listaIstraga) {
            onView(withId(R.id.odabirIstrazivanja)).perform(click())
            onData(allOf(Is(instanceOf(String::class.java)), Is(odabir))).perform(click())
        }
        var listaGrupa = listOf<String>("Grupa","Grupa1")
        for (odabir in listaGrupa) {
            onView(withId(R.id.odabirGrupa)).perform(click())
            onData(allOf(Is(instanceOf(String::class.java)), Is(odabir))).perform(click())
        }
        onView(withId(R.id.dodajIstrazivanjeDugme)).perform(click())
        onView(withSubstring("Uspješno ste upisani u grupu")).check(matches(isDisplayed()))
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(0))
    }

    @Test
    fun testDrugiZadatak() {
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToFirst())
        onView(withId(R.id.filterAnketa)).perform(click())
        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("Sve moje ankete"))).perform(click())
        val ankete = AnketaRepository.getMyAnkete()
        onView(withId(R.id.listaAnketa)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                CoreMatchers.allOf(hasDescendant(withText(ankete[0].naziv)),
            hasDescendant(withText(ankete[0].nazivIstrazivanja))), click()))
        val pitanja = PitanjeAnketaRepository.getPitanja(ankete[0].naziv, ankete[0].nazivIstrazivanja)
        for ((indeks) in pitanja.withIndex()) {
            onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(indeks))
            onView(AllOf.allOf(isDisplayed(), withId(R.id.tekstPitanja))).check(matches(withText(pitanja[indeks].tekst)))
        }
    }
}