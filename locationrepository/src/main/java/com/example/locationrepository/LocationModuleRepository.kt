package com.example.locationrepository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface LocationModuleRepository {

    @Binds
    fun providerPokemonRepository(locationRepositotyImple: LocationRepositotyImple) : LocationRepository
}