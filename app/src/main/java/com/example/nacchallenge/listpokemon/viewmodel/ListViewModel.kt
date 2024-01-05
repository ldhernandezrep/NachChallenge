package com.example.nacchallenge.listpokemon.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.domain.usecases.pokemon.IGetAllPokemonUseCase
import com.example.nacchallenge.listpokemon.ListViewState
import com.models.lpokemon.model.PokemonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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

    val pokemonList: Flow<PagingData<PokemonModel>> = getAllPokemonUseCase.invoke()

}