package com.repository.pokemonrepository.list

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.network.common.NetworkErrorType
import com.example.network.common.NetworkResult
import com.example.network.pokemons.PokemonListService
import com.models.lpokemon.model.PokemonDetail
import com.models.lpokemon.model.PokemonModel
import com.repository.pokemonrepository.mapper.map
import com.repository.pokemonrepository.mapper.toModel
import com.storage.local.dao.PokemonDetailDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositroyImple @Inject constructor (private val pokemonNetwork: PokemonListService, private val pokemonLocal: PokemonDetailDao) : PokemonRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemonList(): Flow<PagingData<PokemonModel>> {

        val pager = Pager(
            config = PagingConfig(pageSize = 25),
            remoteMediator = PokemonDataMediator(pokemonLocal,pokemonNetwork),
            pagingSourceFactory = { pokemonLocal.pagingSource() }
        )

        return pager.flow.map { entity -> entity.map { it.toModel() } }
        }

    override suspend fun getPokemonByName(name: String): PokemonDetail {
       when(val response = pokemonNetwork.getPokemonByName(name)){
           is NetworkResult.NetWorkSuccess -> {
               return response.result.map()
           }
           is NetworkResult.NetworkFailure -> {
               when(val  type = response.networkError.type ){
                   NetworkErrorType.CONNECTION_ERROR -> throw Exception("No hay internet")
                   else -> {
                       throw Exception("Error desconocido")
                   }
               }
           }
       }
    }
}