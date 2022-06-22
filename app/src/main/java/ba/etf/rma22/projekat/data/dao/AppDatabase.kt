package ba.etf.rma22.projekat.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ba.etf.rma22.projekat.data.models.*

@Database(entities = arrayOf(Account::class, Grupa::class, Istrazivanje::class, Anketa::class, Pitanje::class, Odgovor::class ), version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun accountDao(): AccountDAO
    abstract fun grupaDao(): GrupaDAO
    abstract fun anketaDao(): AnketaDAO
    abstract fun istrazivanjeDao(): IstrazivanjeDAO
    abstract fun pitanjeDao(): PitanjeDAO
    abstract fun odgovorDao(): OdgovorDAO

    companion object{
        private var INSTANCE: AppDatabase? = null

        fun setInstance(appdb:AppDatabase):Unit{
            INSTANCE=appdb
        }
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }
        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "RMA22DB"
            ).build()
    }
}