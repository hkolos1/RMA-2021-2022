package ba.etf.rma22.projekat.data.staticdata

import ba.etf.rma22.projekat.data.models.User

fun user(): User {
    return User(
        0,
        mutableListOf(
        "Istraživanje broj 1",
        "Istraživanje broj 9",
        "Istraživanje broj 2",
        "Istraživanje broj 4",
        "Istraživanje broj 10"
        ),
        mutableListOf(
            "Grupa1",
            "Grupa3",
            "Grupa1",
            "Grupa2",
            "Grupa1"
        )
    )
}