package com.example.network.pokemons

import com.example.network.api.PokemonApi
import com.example.network.common.MapResultError.safeApiCall
import com.example.network.common.NetworkResult
import com.example.network.pokemons.model.response.PokemonDetailResponse
import javax.inject.Inject

class PokemonListServiceImp @Inject constructor (private val pokemonApi: PokemonApi) : PokemonListService {

    override suspend fun getAll(limit:Int, offset:Int) =
        safeApiCall{
            pokemonApi.getAll(limit,offset)
        }


    /*override suspend fun load(params: LoadParams<Int>): LoadResult<Int,Result> {
        return try {
            val offset = params.key ?: 0
            val limit = params.loadSize
            var response: ListPokemonResponse
            withContext(Dispatchers.IO) {
               response = pokemonApi.getAll(limit, offset)
            }
            val nextOffset = offset + limit

            LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextOffset
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }*/

    override suspend fun getPokemonByName(name: String): NetworkResult<PokemonDetailResponse> =
        safeApiCall{
            pokemonApi.getPokemonByName(name)
        }
}