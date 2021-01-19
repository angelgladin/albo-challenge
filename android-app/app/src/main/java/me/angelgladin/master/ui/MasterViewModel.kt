package me.angelgladin.master.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import kotlinx.coroutines.CoroutineScope
import me.angelgladin.master.data.BeerRepository

class MasterViewModel @ViewModelInject constructor(
    private val repository: BeerRepository,
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    private val loadTrigger = MutableLiveData(Unit)

    init {
        refresh()
    }

    fun refresh() {
        loadTrigger.value = Unit
    }

    val beers = loadTrigger.switchMap { repository.observeBeers(coroutineScope) }

}