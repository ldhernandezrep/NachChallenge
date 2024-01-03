package com.example.mylocations

import com.example.locationmodel.Location

sealed class LocationViewSate {
    object Loading: LocationViewSate()
    class ErrorLoadingItems(val message: String) : LocationViewSate()
    class LocationSearch(val location: Location) : LocationViewSate()
    object SaveLocation : LocationViewSate()
}