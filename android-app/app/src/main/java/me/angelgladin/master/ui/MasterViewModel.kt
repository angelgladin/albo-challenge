package me.angelgladin.master.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import me.angelgladin.master.data.BeerRepository

class MasterViewModel @ViewModelInject constructor(
    private val repository: BeerRepository,
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    private val loadTrigger = MutableLiveData(Unit)

    fun refresh() {
        loadTrigger.value = Unit
    }
    init {
        refresh()
    }
    val beers = this.repository.observeBeers(coroutineScope)

}