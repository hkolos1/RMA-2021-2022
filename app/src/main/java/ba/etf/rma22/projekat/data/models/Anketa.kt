package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Anketa(
    @SerializedName("id") var id: Int,
    @SerializedName("naziv") var naziv: String,
    @SerializedName("datumPocetak") var datumPocetak: Date,
    @SerializedName("datumKraj") var datumKraj: Date,
    @SerializedName("trajanje") var trajanje: Int,
    @SerializedName("nazivIstrazivanja") var nazivIstrazivanja: String,
    @SerializedName("datumRada") var datumRada: Date?,
    @SerializedName("progres") var progres: Int,
    @SerializedName("nazivGrupe") var nazivGrupe: String,
)