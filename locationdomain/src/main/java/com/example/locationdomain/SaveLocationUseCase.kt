package com.example.locationdomain

import com.example.locationmodel.Location
import com.example.locationrepository.LocationRepository
import javax.inject.Inject

interface ISaveLocationUseCase{
    suspend fun invoke(location: Location)
}

class SaveLocationUseCase @Inject constructor (private val locationRepository: LocationRepository) : ISaveLocationUseCase {

    override suspend fun invoke(location: Location) = locationRepository.saveLocation(location)
}