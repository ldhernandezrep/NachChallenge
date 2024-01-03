package com.example.mylocations

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locationdomain.ISaveLocationUseCase
import com.example.locationdomain.IUpdateLocationUseCase
import com.example.locationmodel.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val updateLocationUseCase: IUpdateLocationUseCase,
    private val saveLocationUseCase: ISaveLocationUseCase
) :
    ViewModel(), LifecycleObserver {

    private val _viewState = MutableLiveData<LocationViewSate>()
    fun getViewState() = _viewState

    fun fetchLocations() {
        viewModelScope.launch {
            try {
                _viewState.value = LocationViewSate.Loading
                updateLocationUseCase.invoke {
                    _viewState.value = LocationViewSate.LocationSearch(location = it)
                }
            } catch (exception: Exception) {
                _viewState.value =
                    LocationViewSate.ErrorLoadingItems(exception.message ?: "Unknown error")
            }
        }
    }

    fun saveLocation(location: Location) {
        viewModelScope.launch {
            try {
                _viewState.value = LocationViewSate.Loading
                saveLocationUseCase.invoke(location)
                _viewState.value = LocationViewSate.SaveLocation
            } catch (exception: Exception) {
                _viewState.value =
                    LocationViewSate.ErrorLoadingItems(exception.message ?: "Unknown error")
            }
        }
    }

}