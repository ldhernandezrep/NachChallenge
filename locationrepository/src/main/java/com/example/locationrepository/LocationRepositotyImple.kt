package com.example.locationrepository

import com.example.locationlocal.LocationData
import com.example.locationmodel.Location
import com.example.locationremote.LocationApi
import javax.inject.Inject

class LocationRepositotyImple @Inject constructor(private val locationData: LocationData,private val locationApi: LocationApi) : LocationRepository {
    override suspend fun startLocationUpdates(callback: (Location) -> Unit) = locationData.startLocationUpdates(callback)
    override suspend fun saveLocation(location: Location) = locationApi.saveLocation(location)

    override suspend fun getAllLocations(callback: (List<Location>) -> Unit) = locationApi.getAllLocations(callback)
}