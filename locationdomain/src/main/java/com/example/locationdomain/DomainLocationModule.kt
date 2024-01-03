package com.example.locationdomain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainLocationModule {

    @Binds
    fun providerUpdateLocationUseCase(updateLocationUseCase: UpdateLocationUseCase) : IUpdateLocationUseCase

    @Binds
    fun providerSaveLocationUseCase(saveLocationUseCase: SaveLocationUseCase) : ISaveLocationUseCase

    @Binds
    fun providerGetAllLocationUseCase(getAllLocationUseCase: GetAllLocationUseCase) : IGetAllLocationUseCase

}