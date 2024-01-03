package com.example.locationlocal

import com.example.locationmodel.Location

interface LocationData {

    suspend fun getLastKnownLocation(): Location?
    suspend fun startLocationUpdates(callback: (Location) -> Unit)
    suspend fun stopLocationUpdates()
}