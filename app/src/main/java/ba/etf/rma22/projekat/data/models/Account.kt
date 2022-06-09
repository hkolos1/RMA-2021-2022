package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

data class Account(
    @SerializedName("id") val id: Int,
    @SerializedName("student") val student: String,
    @SerializedName("acHash") val acHash: String) {
}