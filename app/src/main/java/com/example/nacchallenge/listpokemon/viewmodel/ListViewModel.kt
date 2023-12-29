package com.example.nacchallenge.listpokemon.viewmodel

import com.example.nacchallenge.listpokemon.ListViewState
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor() :
    ViewModel(), LifecycleObserver {

    private val _viewState = MutableLiveData<ListViewState>()
    fun getViewState() = _viewState

    fun fetchPokemons() {
        viewModelScope.launch {
            try {
            } catch (exception: Exception) {
                _viewState.value =
                    ListViewState.ErrorLoadingItems(exception.message ?: "Unknown error")
            }
        }
    }


    fun setSearchQuery(query: String) {
        viewModelScope.launch {
            try {


            } catch (exception: Exception) {
                _viewState.value = ListViewState.ErrorLoadingItems(exception.message ?: "Unknown error")
            }
        }
    }

}