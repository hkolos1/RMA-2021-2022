package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.User
import ba.etf.rma22.projekat.data.staticdata.user

class UserRepository {
    companion object {
        var user: User = user()
        var odabranoIstrazivanje = 0
        var odabranaGrupa = 0
        var porukaIstrazivanje = ""
        var porukaGrupa = ""
    }
}