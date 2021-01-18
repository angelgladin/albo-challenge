package me.angelgladin.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Beer(
    val image_url: String,
    val name: String,
    @Json(name = "tagline") val tagLine: String,
    val description: String,
    @Json(name = "first_brewed") val firstBrewed: String,
    @Json(name = "food_pairing") val foodPairing: List<String>
)