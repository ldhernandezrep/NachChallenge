package com.example.mylocations

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.locationdomain.IGetAllLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListLocationViewModel @Inject constructor(
    private val getAllLocationUseCase: IGetAllLocationUseCase,
) :
    ViewModel(), LifecycleObserver {

    private val _viewState = MutableLiveData<ListLocationViewState>()
    fun getViewState() = _viewState

    init {
        fetchLocations()
    }

    fun fetchLocations() {
        viewModelScope.launch {
            try {
                _viewState.value = ListLocationViewState.Loading
                getAllLocationUseCase.invoke {
                    _viewState.value = ListLocationViewState.LocationsSearch(locations = it)
                }
            } catch (exception: Exception) {
                _viewState.value =
                    ListLocationViewState.ErrorLoadingItems(exception.message ?: "Unknown error")
            }
        }
    }
}