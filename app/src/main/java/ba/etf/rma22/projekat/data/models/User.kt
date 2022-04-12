package ba.etf.rma22.projekat.data.models

data class User (
    var trenutnaGodina : Int,
    var istrazivanja : MutableList<String>,
    var grupe : MutableList<String>
)