package com.example.locationremote

import com.example.locationmodel.Location
import com.google.firebase.firestore.CollectionReference
import javax.inject.Inject

class LocationRemoteImp @Inject constructor(val locationsCollection: CollectionReference) : LocationApi{



    override suspend fun saveLocation(location: Location) {

        locationsCollection.add(location)
            .addOnSuccessListener { documentReference ->
                println("Ubicación guardada con ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Error al guardar la ubicación: $e")
            }
    }

    override suspend fun getAllLocations(callback: (List<Location>) -> Unit) {
        locationsCollection.get()
            .addOnSuccessListener { querySnapshot ->
                val locationsList:MutableList<Location> = mutableListOf<Location>()

                for (document in querySnapshot) {
                    val latitude = document.getDouble("latitiud") ?: 0.0
                    val longitude = document.getDouble("longitud") ?: 0.0
                    val dateLocation = document.getString("fechaCaptura") ?: ""

                    locationsList.add(Location(latitude,longitude,dateLocation))
                }

                callback(locationsList)
            }
            .addOnFailureListener { e ->
                callback(emptyList())
            }
    }
}