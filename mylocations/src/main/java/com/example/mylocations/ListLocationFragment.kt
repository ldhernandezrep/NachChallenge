package com.example.mylocations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mylocations.common.observe
import com.example.mylocations.databinding.FragmentListLocationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListLocationFragment : Fragment(R.layout.fragment_list_location) {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentListLocationBinding
    private val viewModel: ListLocationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListLocationBinding.bind(view)
        lifecycle.addObserver(viewModel)
        observe(viewModel.getViewState(), ::onViewState)
    }

    private fun onViewState(state: ListLocationViewState?) {
        when (state) {
            ListLocationViewState.Loading -> {

            }

            is ListLocationViewState.LocationsSearch -> {
                    val adapter =
                        LocationAdapter(state.locations)
                    val linearLayoutManager = LinearLayoutManager(context)
                    binding.rcvListLocation.layoutManager = linearLayoutManager
                    binding.rcvListLocation.adapter = adapter
            }

            is ListLocationViewState.ErrorLoadingItems -> {

            }

            else -> {}
        }
    }

}