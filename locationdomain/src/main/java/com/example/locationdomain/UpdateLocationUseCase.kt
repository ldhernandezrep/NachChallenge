package com.example.locationdomain

import com.example.locationmodel.Location
import com.example.locationrepository.LocationRepository
import javax.inject.Inject

interface IUpdateLocationUseCase{
    suspend fun invoke(callback: (Location) -> Unit)
}

class UpdateLocationUseCase @Inject constructor (private val locationRepository: LocationRepository) : IUpdateLocationUseCase {

    override suspend fun invoke(callback: (Location) -> Unit) = locationRepository.startLocationUpdates(callback)
}