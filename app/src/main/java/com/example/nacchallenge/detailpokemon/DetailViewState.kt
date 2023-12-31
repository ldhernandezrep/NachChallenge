package com.example.nacchallenge.detailpokemon

import com.models.lpokemon.model.PokemonDetail

sealed class DetailViewState {
    object Loading: DetailViewState()
    object ErrorLoadingItems : DetailViewState()
    class ItemsLoaded(val pokemon: com.models.lpokemon.model.PokemonDetail) : DetailViewState()

}