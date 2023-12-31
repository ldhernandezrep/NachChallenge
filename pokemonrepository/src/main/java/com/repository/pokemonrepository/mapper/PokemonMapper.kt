package com.repository.pokemonrepository.mapper

import com.example.network.pokemons.model.response.ListPokemonResponse
import com.example.network.pokemons.model.response.Result
import com.models.lpokemon.model.ListPokemonModel
import com.models.lpokemon.model.PokemonListModel
import com.models.lpokemon.model.PokemonModel
import com.storage.local.entities.PokemonEntity

fun ListPokemonResponse.toMapper() = ListPokemonModel(
    count = this.count,
    next = this.next,
    previous = this.previous,
    results = this.results.toLisModdel()
)


fun List<Result>.toLisModdel(): PokemonListModel {
    val resultList = mutableListOf<PokemonModel>()
    this.forEach {
        resultList.add(it.toModel())
    }

    return PokemonListModel(resultList)
}

fun Result.toModel() = PokemonModel(
    name = this.name,
    url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${
        getDigitPokemon(
            this.url
        )
    }.png",
    number = getDigitPokemon(this.url),
)

fun PokemonModel.toEntity() = PokemonEntity(
    id = this.number.toString(),
    namePokemon = this.name,
    height = 0,
    weight = 0
)

fun PokemonEntity.toModel() = PokemonModel(
    number = this.id.toInt(),
    name = this.namePokemon,
    url = ""
)

fun getDigitPokemon(url: String): Int {
    val regex = Regex("/(\\d+)/$")
    val matchResult = regex.find(url)
    val numeroPokemon = matchResult?.groups?.get(1)?.value
    val numeroPokemonInt = numeroPokemon?.toIntOrNull()
    return numeroPokemonInt!!
}