package com.example.locationdomain

import com.example.locationmodel.Location
import com.example.locationrepository.LocationRepository
import javax.inject.Inject

interface IGetAllLocationUseCase{
    suspend fun invoke(callback: (List<Location>) -> Unit)
}

class GetAllLocationUseCase @Inject constructor (private val locationRepository: LocationRepository) : IGetAllLocationUseCase {

    override suspend fun invoke(callback: (List<Location>) -> Unit) = locationRepository.getAllLocations(callback)
}