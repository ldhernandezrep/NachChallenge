package com.example.domain.usecases.pokemon

import com.models.lpokemon.model.PokemonDetail
import com.repository.pokemonrepository.list.PokemonRepository
import javax.inject.Inject

interface IGetPokemonUseCase{
    suspend fun invoke(name:String): PokemonDetail
}

class GetPokemonUseCase @Inject constructor (private val pokemonRepository: PokemonRepository) : IGetPokemonUseCase {

        override suspend fun invoke(name:String): PokemonDetail {
            return pokemonRepository.getPokemonByName(name)
        }
    }