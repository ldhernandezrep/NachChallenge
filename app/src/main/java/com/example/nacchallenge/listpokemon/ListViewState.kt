package com.example.nacchallenge.listpokemon

import PokemonModel
import androidx.paging.PagingData

sealed class ListViewState {

    object Loading: ListViewState()
    class ErrorLoadingItems(val message: String) : ListViewState()
    class ItemsSearch(val pokemons: PagingData<PokemonModel>) : ListViewState()

}