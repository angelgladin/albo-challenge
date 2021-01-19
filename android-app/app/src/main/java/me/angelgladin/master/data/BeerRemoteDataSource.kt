package me.angelgladin.master.data

import me.angelgladin.data.BaseDataSource
import me.angelgladin.data.BeerService
import javax.inject.Inject

class BeerRemoteDataSource @Inject constructor(private val service: BeerService) :
    BaseDataSource() {

    suspend fun fetchData(page: String, perPage: String) =
        getResult { service.getBeers(page, perPage) }
}