package com.storage.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.storage.local.dao.PokemonDetailDao
import com.storage.local.entities.PokemonEntity

@Database(
    entities = [
        PokemonEntity::class,
    ],
    version = 2
)
abstract class NachChallangeDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDetailDao
}