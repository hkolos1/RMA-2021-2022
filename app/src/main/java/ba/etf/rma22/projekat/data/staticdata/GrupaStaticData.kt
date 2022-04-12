package ba.etf.rma22.projekat.data.staticdata

import ba.etf.rma22.projekat.data.models.Grupa

/*---StaticData za grupe---*/
fun grupe(): List<Grupa> {
    return listOf(

        Grupa("Grupa1", "Istraživanje broj 1"),
        Grupa("Grupa1", "Istraživanje broj 2"),
        Grupa("Grupa1", "Istraživanje broj 3"),

        Grupa("Grupa2", "Istraživanje broj 4"),
        Grupa("Grupa2", "Istraživanje broj 5"),
        Grupa("Grupa2", "Istraživanje broj 6"),

        Grupa("Grupa3", "Istraživanje broj 7"),
        Grupa("Grupa3", "Istraživanje broj 8"),
        Grupa("Grupa3", "Istraživanje broj 9"),

        Grupa("Grupa1", "Istraživanje broj 10"),
        Grupa("Grupa1", "Istraživanje broj 11"),
        Grupa("Grupa2", "Istraživanje broj 12"),

        Grupa("Grupa2", "Istraživanje broj 13"),
        Grupa("Grupa3", "Istraživanje broj 14"),
        Grupa("Grupa3", "Istraživanje broj 15"),


    )
}