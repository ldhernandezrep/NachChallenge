package com.example.locationlocal

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.example.locationmodel.Location
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import javax.inject.Inject

class LocationDataImpl @Inject constructor(private val context: Context) : LocationData {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun getLastKnownLocation(): Location? {
        return try {
            val locationGps = fusedLocationClient.lastLocation.result
            val location = Location(locationGps.latitude, locationGps.longitude,"")
            location
        } catch (e: Exception) {
            null
        }
    }

    @SuppressLint("MissingPermission")
    override suspend fun startLocationUpdates(callback: (Location) -> Unit) {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.lastLocation?.let { location ->
                    callback.invoke(Location(location.latitude,location.longitude,""))
                }
            }
        }

        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(5000) // Intervalo de actualización de 5 segundos
            .setFastestInterval(2000) // Actualización más rápida en 2 segundos

        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            ).result
        } catch (e: Exception) {
            // Manejar errores
        }
    }

    override suspend fun stopLocationUpdates() {
        try {
            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult?) {
                    locationResult?.lastLocation?.let { location ->

                    }
                }
            }
            fusedLocationClient.removeLocationUpdates(locationCallback)
        } catch (e: Exception) {
            // Manejar errores
        }
    }
}