package ba.etf.rma22.projekat.data.repositories

class AccountRepository {
    companion object {
        var acHash: String = "2d5ceafb-0fd2-4282-b767-0a9740cd2c20"

        fun postaviHash(accHash: String): Boolean {
            try {
                acHash = accHash
                return true
            } catch (e: Exception) {
                return false
            }
        }

        fun getHash(): String {
            return acHash
        }

    }
}