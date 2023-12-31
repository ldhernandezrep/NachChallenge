package com.repository.pokemonrepository.list

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.network.pokemons.PokemonListService
import com.repository.pokemonrepository.mapper.toEntity
import com.repository.pokemonrepository.mapper.toModel
import com.storage.local.dao.PokemonDetailDao
import com.storage.local.entities.PokemonEntity

@OptIn(ExperimentalPagingApi::class)
class PokemonDataMediator(
    private val beerDb: PokemonDetailDao,
    private val beerApi: PokemonListService
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
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id.toInt() / state.config.pageSize) + 1
                    }
                }
            }

            val beers = beerApi.getAll(
                limit = loadKey,
                offset = state.config.pageSize
            )


            if (loadType == LoadType.REFRESH) {
                beerDb.clearAll()
            }
            val pokemonEntities = beers.results.map { it.toModel().toEntity() }
            beerDb.upsertAll(pokemonEntities)

            MediatorResult.Success(
                endOfPaginationReached = beers.results.isEmpty()
            )
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}