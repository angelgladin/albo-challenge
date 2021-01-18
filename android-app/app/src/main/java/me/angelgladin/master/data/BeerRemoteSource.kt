package me.angelgladin.master.data

import me.angelgladin.data.BaseDataSource
import me.angelgladin.data.BeerService
import javax.inject.Inject

class BeerRemoteSource @Inject constructor(private val service: BeerService) : BaseDataSource(){

    suspend fun fetchData() = getResult { service.getBeers("10", "20") }
}