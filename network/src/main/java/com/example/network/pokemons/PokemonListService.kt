package com.example.network.pokemons

import com.example.network.common.NetworkResult
import com.example.network.pokemons.model.response.ListPokemonResponse
import com.example.network.pokemons.model.response.PokemonDetailResponse

interface PokemonListService {
    suspend fun getAll(limit:Int, offset:Int): NetworkResult<ListPokemonResponse>
    suspend fun getPokemonByName(name:String): NetworkResult<PokemonDetailResponse>
}