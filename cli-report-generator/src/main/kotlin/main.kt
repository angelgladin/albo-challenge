import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.io.File

fun main(args: Array<String>) {
    // Root path of the json
    val fileName = "src/main/resources/transactions.json"
    // Since it's a "small" file, we can read it all and put it in a String
    val content = File(fileName).readText()

    // Parsing the json
    val moshi = Moshi.Builder().build()
    val type = Types.newParameterizedType(MutableList::class.java, Transaction::class.java)
    val jsonAdapter = moshi.adapter<List<Transaction>>(type)
    val transactions = jsonAdapter.fromJson(content)
    println(transactions)
}

@JsonClass(generateAdapter = true)
data class Transaction(
    val uuid: String
)