package com.example.nacchallenge.listpokemon

import com.example.domain.usecases.pokemon.model.PokemonModel

interface IClickPokemonListener {
    fun onClick(pokemonItem: PokemonModel)
}