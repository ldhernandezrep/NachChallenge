package com.storage.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "pokemon"
)
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val namePokemon: String,
    val height: Int,
    val url: String,
    val weight: Int,
)
