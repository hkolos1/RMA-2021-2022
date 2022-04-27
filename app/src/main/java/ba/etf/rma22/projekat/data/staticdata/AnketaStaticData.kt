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
    return listOf(
        Anketa(
            "Anketa 1",
            "Istraživanje broj 1",
            datum(10,4,2022),
            datum(10,4,2022),
            datum(10,4,2022),
            2,"Grupa1", 1.0f
        ),
        Anketa(
            "Anketa 2",
            "Istraživanje broj 2",
            datum(10,8,2022),
            datum(10,9,2022),
            null,
            5,"Grupa1", 0.0f
        ),
        Anketa(
            "Anketa 3",
            "Istraživanje broj 3",
            datum(2,4,2022),
            datum(14,5,2022),
            datum(3,4,2022),
            5,"Grupa1", 1.0f
        ),
        Anketa(
            "Anketa 4",
            "Istraživanje broj 4",
            datum(1,4,2021),
            datum(2,7,2022),
            null,
            5,"Grupa2", 0.5f
        ),
        Anketa(
            "Anketa 5",
            "Istraživanje broj 5",
            datum(2,9,2022),
            datum(2,10,2022),
            null,
            5,"Grupa2", 0.0f
        ),
        Anketa(
            "Anketa 6",
            "Istraživanje broj 6",
            datum(10,10,2022),
            datum(10,11,2022),
            null,
            5,"Grupa2", 0.0f
        ),
        Anketa(
            "Anketa 7",
            "Istraživanje broj 7",
            datum(4,4,2022),
            datum(25,8,2022),
            null,
            5,"Grupa3", 0.8f
        ),
        Anketa(
            "Anketa 8",
            "Istraživanje broj 8",
            datum(2,4,2022),
            datum(10,7,2022),
            null,
            5,"Grupa3", 0.4f
        ),
        Anketa(
            "Anketa 9",
            "Istraživanje broj 9",
            datum(2,3,2022),
            datum(3,4,2022),
            null,
            5,"Grupa3", 0.8f
        ),
        Anketa(
            "Anketa 10",
            "Istraživanje broj 10",
            datum(2,4,2022),
            datum(2,8,2022),
            null,
            5,"Grupa1", 0.4f
        ),
        Anketa(
            "Anketa 11",
            "Istraživanje broj 11",
            datum(3,9,2022),
            datum(10,9,2022),
            null,
            5,"Grupa1", 0.0f
        ),
        Anketa(
            "Anketa 12",
            "Istraživanje broj 12",
            datum(2,4,2022),
            datum(10,9,2022),
            null,
            5,"Grupa2", 0.4f
        ),
        Anketa(
            "Anketa 13",
            "Istraživanje broj 13",
            datum(2,4,2022),
            datum(4,4,2022),
            null,
            5,"Grupa2", 0.5f
        ),
        Anketa(
            "Anketa 14",
            "Istraživanje broj 14",
            datum(2,4,2022),
            datum(14,4,2022),
            datum(3,4,2022),
            5,"Grupa3", 1.0f
        ),
        Anketa(
            "Anketa 15",
            "Istraživanje broj 15",
            datum(2,4,2022),
            datum(17,8,2022),
            datum(3,4,2022),
            5,"Grupa3", 1.0f
        )

    )
}