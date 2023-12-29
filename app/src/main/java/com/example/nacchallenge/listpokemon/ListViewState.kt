package com.example.nacchallenge.listpokemon

import androidx.paging.PagingData
import com.example.domain.usecases.pokemon.model.PokemonModel

sealed class ListViewState {

    object Loading: ListViewState()
    class ErrorLoadingItems(val message: String) : ListViewState()
    class ItemsSearch(val pokemons: PagingData<PokemonModel>) : ListViewState()

}