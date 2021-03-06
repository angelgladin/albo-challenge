package me.angelgladin.master.data

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import android.util.Log
import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.angelgladin.data.Result.Status
import me.angelgladin.data.entity.Beer
import javax.inject.Inject

class BeerPageDataSource @Inject constructor(
    private val dao: BeerDao,
    private val remoteDataSource: BeerRemoteDataSource,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Beer>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Beer>
    ) {
        fetchData(1) {
            callback.onResult(it, null, 2)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Beer>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page - 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Beer>) {
        val page = params.key
        fetchData(page) {
            callback.onResult(it, page + 1)
        }
    }

    private fun fetchData(page: Int, callback: (List<Beer>) -> Unit) {
        scope.launch(getJobErrorHandler()) {
            val response = remoteDataSource.fetchData("$page", "$PAGE_SIZE")
            if (response.status == Status.SUCCESS) {
                val results = response.data!!
                dao.insertAll(results)
                callback(results)
            } else if (response.status == Status.ERROR) {
                val source = dao.getBeers()
                callback(source)
                postError(response.message!!)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Log.e("BeerPageDataSource", "An error happened: $message")
    }
}