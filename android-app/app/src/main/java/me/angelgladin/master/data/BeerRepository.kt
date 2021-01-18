package me.angelgladin.master.data

import me.angelgladin.data.resultLiveData
import javax.inject.Inject

class BeerRepository @Inject constructor(
    private val dao: BeerDao,
    private val remoteSource: BeerRemoteSource
) {

    val beers = resultLiveData(
        databaseQuery = { dao.getBeers() },
        networkCall = { remoteSource.fetchData() },
        saveCallResult = { dao.insertAll(it) })
}