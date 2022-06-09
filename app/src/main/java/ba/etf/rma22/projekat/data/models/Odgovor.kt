package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

data class Odgovor(
    @SerializedName("PitanjeId") var id: Int,
    @SerializedName("odgovoreno") var odgovoreno: Int,
    @SerializedName("AnketaTakenId") var anketaTakenId: Int
    )