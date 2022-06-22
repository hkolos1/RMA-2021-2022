package ba.etf.rma22.projekat.data.models

import androidx.room.TypeConverter

class Converter {

    /*Posuđen kod sa stackoverflowa*/
    @TypeConverter
    fun fromString(lista: List<String>): String {
        var povratniString = ""
        for (i in 0 until lista.size) {
            povratniString+=lista[i]
            if (i != lista.size-1)
                povratniString+=","
        }
        return povratniString
    }
    @TypeConverter
    fun fromList(opcije: String): List<String> {
        return opcije.split(",")
    }
}