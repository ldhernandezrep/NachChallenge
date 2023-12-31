package com.repository.pokemonrepository

import com.repository.pokemonrepository.list.PokemonRepository
import com.repository.pokemonrepository.list.PokemonRepositroyImple
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
        @Binds
        fun providerPokemonRepository(pokemonRepository: PokemonRepositroyImple) : PokemonRepository
}