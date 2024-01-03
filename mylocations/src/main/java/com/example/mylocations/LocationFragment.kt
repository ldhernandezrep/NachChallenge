package com.example.mylocations

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mylocations.common.PermissionManager
import com.example.mylocations.common.observe
import com.example.mylocations.databinding.FragmentLocationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment : Fragment(R.layout.fragment_location) {
    private lateinit var binding: FragmentLocationBinding
    private val permissionManager by lazy { PermissionManager(this) }
    private val viewModel: LocationViewModel by viewModels()

    private val permissionsToRequest = listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLocationBinding.bind(view)

        permissionManager.setOnPermissionsGrantedListener {
            findNavController().navigate(
                R.id.action_locationFragment_to_listLocationFragment,
            )
        }

        permissionManager.setOnPermissionsDeneListener {
            //Que no hacer
        }

        binding.button.setOnClickListener {
            permissionManager.checkAndRequestPermissions(
                permissionsToRequest
            )
        }
        observe(viewModel.getViewState(), ::onViewState)

    }

    private fun onViewState(state: LocationViewSate?) {
        when (state) {
            LocationViewSate.Loading -> {
                binding.llProgressBar.root.visibility = View.VISIBLE
                binding.llyMain.visibility = View.GONE
            }

            is LocationViewSate.LocationSearch -> {
                lifecycleScope.launch {
                    viewModel.saveLocation(state.location)
                }
            }

            is LocationViewSate.SaveLocation -> {
                Log.d("Ubicacion guardada","")
                findNavController().navigate(
                    R.id.action_locationFragment_to_listLocationFragment,
                )
            }

            is LocationViewSate.ErrorLoadingItems -> {
                binding.llProgressBar.root.visibility = View.GONE
                binding.llyMain.visibility = View.VISIBLE
            }

            else -> {}
        }
    }
}