package com.example.nacchallenge.listpokemon

import androidx.paging.PagingData
import com.models.lpokemon.model.PokemonModel

sealed class ListViewState {

    object Loading: ListViewState()
    class ErrorLoadingItems(val message: String) : ListViewState()
    class ItemsSearch(val pokemons: PagingData<com.models.lpokemon.model.PokemonModel>) : ListViewState()

}