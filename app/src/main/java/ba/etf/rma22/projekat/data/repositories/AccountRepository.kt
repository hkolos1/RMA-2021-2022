package ba.etf.rma22.projekat.data.repositories

import android.content.Context
import ba.etf.rma22.projekat.data.dao.AppDatabase
import ba.etf.rma22.projekat.data.models.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountRepository {
    suspend fun postaviHash(accHash: String): Boolean {
        return withContext(Dispatchers.IO) {
            acHash = accHash
            try {
                val baza = AppDatabase.getInstance(context)
                baza.accountDao().deleteAccount()
                try {
                    baza.accountDao().deleteAccount()
                    val acc = ApiAdapter.retrofit.dajAccount(accHash)
                    baza.accountDao().ubaciAccount(acc)
                } catch (e: Exception) {
                    baza.accountDao().deleteAccount()
                    val acc = Account(0, "", accHash)
                    baza.accountDao().ubaciAccount(acc)
                }
                baza.odgovorDao().obrisiBazu()
                baza.anketaDao().obrisiBazu()
                baza.grupaDao().obrisiBazu()
                baza.pitanjeDao().obrisiBazu()
                baza.istrazivanjeDao().obrisiBazu()
                return@withContext true
            } catch (e: Exception) {
                return@withContext false
            }
        }
    }

    companion object {
        fun getHash(): String {
            return acHash
        }

        var acHash: String = "2d5ceafb-0fd2-4282-b767-0a9740cd2c20"
        private lateinit var context: Context
        fun setContext(_context: Context) {
            context = _context
        }

        fun getContext(): Context {
            return context
        }

        suspend fun postaviHash(accHash: String): Boolean {
            return withContext(Dispatchers.IO) {
                acHash = accHash
                try {
                    val baza = AppDatabase.getInstance(context)
                    baza.accountDao().deleteAccount()
                    try {
                        baza.accountDao().deleteAccount()
                        val acc = ApiAdapter.retrofit.dajAccount(accHash)
                        baza.accountDao().ubaciAccount(acc)
                    } catch (e: Exception) {
                        baza.accountDao().deleteAccount()
                        val acc = Account(0, "", accHash)
                        baza.accountDao().ubaciAccount(acc)
                    }
                    baza.anketaDao().obrisiBazu()
                    baza.istrazivanjeDao().obrisiBazu()
                    baza.grupaDao().obrisiBazu()
                    baza.pitanjeDao().obrisiBazu()
                    baza.odgovorDao().obrisiBazu()

                    return@withContext true
                } catch (e: Exception) {
                    return@withContext false
                }
            }
        }


        suspend fun dajAccount(): Account? {
            return withContext(Dispatchers.IO) {
                try {
                    val db = AppDatabase.getInstance(context)
                    val rez = db.accountDao().dajAccount()
                    return@withContext rez
                } catch (e: Exception) {
                    return@withContext null
                }
            }
        }
    }


}