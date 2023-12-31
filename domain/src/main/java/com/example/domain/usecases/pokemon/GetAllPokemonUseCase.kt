package com.example.domain.usecases.pokemon

import androidx.paging.PagingData
import com.models.lpokemon.model.PokemonModel
import com.repository.pokemonrepository.list.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IGetAllPokemonUseCase{
    fun invoke(): Flow<PagingData<PokemonModel>>
}

class GetAllPokemonUseCase @Inject constructor (private val pokemonRepository: PokemonRepository) : IGetAllPokemonUseCase {

    override fun invoke(): Flow<PagingData<PokemonModel>> {
        return pokemonRepository.getPokemonList()
    }
}

