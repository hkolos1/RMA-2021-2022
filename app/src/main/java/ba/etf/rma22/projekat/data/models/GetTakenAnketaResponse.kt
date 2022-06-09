package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class GetTakenAnketaResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("student") var student: String,
    @SerializedName("datumRada") var datumRada: Date?,
    @SerializedName("progres") var progres: Float,
    @SerializedName("message") var message: String?
)