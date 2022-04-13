package ba.etf.rma22.projekat.data.staticdata

import ba.etf.rma22.projekat.data.models.User

fun user(): User {
    return User(0,mutableListOf("Istraživanje broj 1","Istraživanje broj 9"), mutableListOf("Grupa1","Grupa3"))

}