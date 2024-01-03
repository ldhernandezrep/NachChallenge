package com.example.locationrepository

import com.example.locationmodel.Location

interface LocationRepository {
    suspend fun startLocationUpdates(callback: (Location) -> Unit)
    suspend fun saveLocation(location: Location)
    suspend fun getAllLocations(callback: (List<Location>) -> Unit)
}