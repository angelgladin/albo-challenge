package me.angelgladin.master.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import me.angelgladin.master.data.BeerRepository

class MasterViewModel @ViewModelInject constructor(private val repository: BeerRepository) :
    ViewModel() {
    val beers = repository.beers
}