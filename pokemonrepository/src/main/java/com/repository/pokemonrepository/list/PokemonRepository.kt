package com.repository.pokemonrepository.list

import androidx.paging.PagingData
import com.models.lpokemon.model.PokemonDetail
import com.models.lpokemon.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<PokemonModel>>
    suspend fun getPokemonByName(name: String): PokemonDetail
}