package com.example.locationremote

import com.example.locationmodel.Location


interface LocationApi {
    suspend fun saveLocation(location: Location)
    suspend fun getAllLocations(callback: (List<Location>) -> Unit)

}