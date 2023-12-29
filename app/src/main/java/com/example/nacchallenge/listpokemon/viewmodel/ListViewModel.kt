package com.example.nacchallenge.listpokemon.viewmodel

import com.example.nacchallenge.listpokemon.ListViewState
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.pokemon.IGetAllPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val getAllPokemonUseCase: IGetAllPokemonUseCase) :
    ViewModel(), LifecycleObserver {

    private val _viewState = MutableLiveData<ListViewState>()
    fun getViewState() = _viewState

    fun fetchPokemons() {
        viewModelScope.launch {
            try {
                getAllPokemonUseCase.invoke()
                    .onStart { _viewState.value = ListViewState.Loading }
                    .catch {
                        _viewState.value =
                            ListViewState.ErrorLoadingItems(it.message ?: "Unknown error")
                    }
                    .collect{
                        _viewState.value = ListViewState.ItemsSearch(pokemons = it)
                    }
            } catch (exception: Exception) {
                _viewState.value =
                    ListViewState.ErrorLoadingItems(exception.message ?: "Unknown error")
            }
        }
    }

}