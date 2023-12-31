package com.example.mylocations

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mylocations.databinding.FragmentListLocationBinding

class ListLocationFragment : Fragment(R.layout.fragment_list_location) {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentListLocationBinding
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)
            //param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListLocationBinding.bind(view)
        val activity = activity as AppCompatActivity?
    }

}