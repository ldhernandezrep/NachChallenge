package com.example.mylocations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mylocations.common.observe
import com.example.mylocations.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapFragment : Fragment(R.layout.fragment_map), OnMapReadyCallback {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentMapBinding
    private val viewModel: ListLocationViewModel by viewModels()
    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
        observe(viewModel.getViewState(), ::onViewState)
    }

    private fun onViewState(state: ListLocationViewState?) {
        when (state) {
            ListLocationViewState.Loading -> {
            }

            is ListLocationViewState.LocationsSearch -> {
                lifecycleScope.launch {
                       state.locations.map {
                           mMap.clear()
                           mMap.addMarker(MarkerOptions().position(LatLng(it.latitiud, it.longitud)).title("Nuevo Marcador "))
                           mMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(it.latitiud, it.longitud)))
                       }
                }
            }

            is ListLocationViewState.ErrorLoadingItems -> {

            }

            else -> {}
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

}