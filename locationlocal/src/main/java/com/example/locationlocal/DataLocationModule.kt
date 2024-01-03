package com.example.locationlocal

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataLocationModule {
    @Provides
    fun providerPokemonData(context: Context) : LocationData{
        return LocationDataImpl(context)
    }
}
