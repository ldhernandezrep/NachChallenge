package com.storage.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.storage.local.entities.PokemonEntity

@Dao
interface PokemonDetailDao {

    @Upsert
    suspend fun upsertAll(beers: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon")
    fun pagingSource(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM pokemon")
    suspend fun clearAll()

}