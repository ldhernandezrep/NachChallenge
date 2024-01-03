package com.repository.pokemonrepository.list

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.network.common.NetworkResult
import com.example.network.pokemons.PokemonListService
import com.repository.pokemonrepository.mapper.toEntity
import com.repository.pokemonrepository.mapper.toModel
import com.storage.local.dao.PokemonDetailDao
import com.storage.local.entities.PokemonEntity

@OptIn(ExperimentalPagingApi::class)
class PokemonDataMediator(
    private val pkemonDb: PokemonDetailDao,
    private val pokemonApi: PokemonListService
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.id?.toInt() ?: 1
                }
            }

            val listPokemonResponse = pokemonApi.getAll(
                limit = state.config.pageSize,
                offset = loadKey
            )


            when(listPokemonResponse){
                is NetworkResult.NetWorkSuccess -> {
                    if (loadType == LoadType.REFRESH) {
                        pkemonDb.clearAll()
                    }
                    val pokemonEntities = listPokemonResponse.result.results.map { it.toModel().toEntity() }
                    pkemonDb.upsertAll(pokemonEntities)

                    MediatorResult.Success(
                        endOfPaginationReached = listPokemonResponse.result.results.isEmpty()
                    )
                }
                is NetworkResult.NetworkFailure -> {
                    MediatorResult.Error(Exception(listPokemonResponse.networkError.rawError))
                }
            }


        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}