package me.angelgladin.data

import me.angelgladin.data.entity.Beer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerService {
    @GET("beers/")
    suspend fun getBeers(
        @Query("page") page: String,
        @Query("per_page") perPage: String
    ): Response<List<Beer>>

    companion object {
        const val BASE_URL = "https://api.punkapi.com/v2/"
    }
}