package me.angelgladin.master.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import kotlinx.coroutines.CoroutineScope
import me.angelgladin.data.entity.Beer
import javax.inject.Inject

class BeerPageDataSourceFactory @Inject constructor(
    private val dao: BeerDao,
    private val remoteDataSource: BeerRemoteDataSource,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, Beer>() {

    private val liveData = MutableLiveData<BeerPageDataSource>()

    override fun create(): DataSource<Int, Beer> {
        val source = BeerPageDataSource(dao, remoteDataSource, scope)

        liveData.postValue(source)
        return source
    }

    companion  object {
        private const val PAGE_SIZE = 20

        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}