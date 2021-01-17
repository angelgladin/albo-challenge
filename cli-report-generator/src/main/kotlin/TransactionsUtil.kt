import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.File

object TransactionsUtil {
    // Root path of the json
    private const val jsonFileWithPath = "src/main/resources/transactions.json"

    val CATEGORIES = arrayOf(
        "Alimentacion",
        "Hogar",
        "Entretenimiento",
        "Servicios",
        "Transferencias",
        "Retiros en cajero",
        "Otros",
        "Transporte"
    )

    fun parseMonthIntToString(n: Int) = when (n) {
        1 -> "Enero"
        2 -> "Febrero"
        3 -> "Marzo"
        4 -> "Abril"
        5 -> "Mayo"
        6 -> "Junio"
        7 -> "Julio"
        8 -> "Agosto"
        9 -> "Septiembre"
        10 -> "Octubre"
        11 -> "Noviembre"
        12 -> "Diciembre"
        else -> ""
    }

    fun retrieveTransactionsFromLocalFile(): List<Transaction> {
        // Since it's a "small" file, we can read it all and put it in a String
        val content = File(jsonFileWithPath).readText()
        // Parsing the json
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(MutableList::class.java, Transaction::class.java)
        val jsonAdapter = moshi.adapter<List<Transaction>>(type)
        return jsonAdapter.fromJson(content)!!
    }
}