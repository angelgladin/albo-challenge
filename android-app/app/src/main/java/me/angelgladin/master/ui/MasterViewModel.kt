package me.angelgladin.master.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import me.angelgladin.master.data.BeerRepository

class MasterViewModel @ViewModelInject constructor(
    private val repository: BeerRepository,
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    val beers = this.repository.observeBeers(coroutineScope)

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }
}