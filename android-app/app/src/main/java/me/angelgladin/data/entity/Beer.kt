package me.angelgladin.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "beer")
data class Beer(
    @PrimaryKey val id: Int,
    val image_url: String,
    val name: String,
    @Json(name = "tagline") val tagLine: String,
    val description: String,
    @Json(name = "first_brewed") val firstBrewed: String
)