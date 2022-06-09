package ba.etf.rma22.projekat.data.staticdata

import ba.etf.rma22.projekat.data.models.Anketa
import java.util.*

fun datum(dan : Int, mjesec : Int, godina : Int) : Date {
    return Calendar.getInstance().run{
        set(godina, mjesec-1, dan)
        time
    }
}

/*---StaticData za ankete---*/
fun ankete(): List<Anketa> {
    return emptyList()
}