package me.angelgladin.master.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import me.angelgladin.data.entity.Beer
import javax.inject.Inject

class BeerRepository @Inject constructor(
    private val dao: BeerDao,
    private val remoteDataSource: BeerRemoteDataSource
) {

    fun observeBeers(coroutineScope: CoroutineScope): LiveData<PagedList<Beer>> {
        val dataSourceFactory = BeerPageDataSourceFactory(dao, remoteDataSource, coroutineScope)

        return LivePagedListBuilder(
            dataSourceFactory,
            BeerPageDataSourceFactory.pagedListConfig()
        ).build()
    }
}