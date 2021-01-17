import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Transaction(
    val uuid: String,
    val description: String,
    @Json(name = "creation_date") val creationDate: String,
    val amount: Double,
    val category: String,
    val operation: String,
    val status: String,
)