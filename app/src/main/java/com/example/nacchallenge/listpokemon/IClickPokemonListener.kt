package com.example.nacchallenge.listpokemon

import com.models.lpokemon.model.PokemonModel

interface IClickPokemonListener {
    fun onClick(pokemonItem: com.models.lpokemon.model.PokemonModel)
}