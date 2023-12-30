package com.storage.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.storage.local.entities.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDetailDao {

    @Query("""
        SELECT * FROM pokemon
    """)
    fun getAllPokemons(): Flow<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnorePokemons(products: List<PokemonEntity>): List<Long>

}