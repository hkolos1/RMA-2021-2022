package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

data class Istrazivanje(
    @SerializedName("id") var id: Int,
    @SerializedName("naziv") var naziv: String,
    @SerializedName("godina") var godina: Int)