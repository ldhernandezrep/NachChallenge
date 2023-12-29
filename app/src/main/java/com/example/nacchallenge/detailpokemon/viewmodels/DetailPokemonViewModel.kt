package com.example.nacchallenge.detailpokemon.viewmodels

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.pokemon.GetPokemonUseCase
import com.example.nacchallenge.detailpokemon.DetailViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPokemonViewModel  @Inject constructor(private val getPokemonUseCase: GetPokemonUseCase) :
    ViewModel(), LifecycleObserver {

    private val _viewState = MutableLiveData<DetailViewState>()
    fun getViewState() = _viewState


    fun fetchPokemon(name: String) {
        _viewState.value = DetailViewState.Loading

        viewModelScope.launch {
            try {
                val response =  getPokemonUseCase.invoke(name)
                _viewState.value =
                    response?.let { DetailViewState.ItemsLoaded(pokemon = it) }

            } catch (ex: Exception) {
                _viewState.value = DetailViewState.ErrorLoadingItems
            }
        }
    }

}