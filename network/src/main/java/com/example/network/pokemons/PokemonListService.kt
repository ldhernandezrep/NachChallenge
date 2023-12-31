package com.example.network.pokemons

import androidx.paging.PagingData
import com.example.network.common.NetworkResult
import com.example.network.common.PokemonResponse
import com.example.network.pokemons.model.response.ListPokemonResponse
import com.example.network.pokemons.model.response.PokemonDetailResponse
import com.example.network.pokemons.model.response.Result
import kotlinx.coroutines.flow.Flow

interface PokemonListService {
    suspend fun getAll(limit:Int, offset:Int): ListPokemonResponse
    suspend fun getPokemonByName(name:String): NetworkResult<PokemonDetailResponse>
}