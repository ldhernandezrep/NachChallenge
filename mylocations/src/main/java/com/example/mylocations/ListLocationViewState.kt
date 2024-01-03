package com.example.mylocations

import com.example.locationmodel.Location

sealed class ListLocationViewState {

    object Loading: ListLocationViewState()
    class ErrorLoadingItems(val message: String) : ListLocationViewState()
    class LocationsSearch(val locations: List<Location>) : ListLocationViewState()

}