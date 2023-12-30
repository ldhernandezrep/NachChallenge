package com.storage.local

import com.storage.local.dao.PokemonDetailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesProductsDao(
        database: NachChallangeDatabase,
    ): PokemonDetailDao = database.pokemonDao()
}