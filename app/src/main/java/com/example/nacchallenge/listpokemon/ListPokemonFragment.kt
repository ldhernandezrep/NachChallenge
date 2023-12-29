package com.example.nacchallenge.listpokemon

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.usecases.pokemon.model.PokemonModel
import com.example.nacchallenge.R
import com.example.nacchallenge.databinding.FragmentListPokemonBinding
import com.example.nacchallenge.listpokemon.adapters.PokemonsAdapterList
import com.example.nacchallenge.listpokemon.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import observe

@AndroidEntryPoint
class ListPokemonFragment : Fragment(R.layout.fragment_list_pokemon), IClickPokemonListener {

    private lateinit var binding: FragmentListPokemonBinding
    private lateinit var pokemonAdapterList: PokemonsAdapterList
    private val viewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListPokemonBinding.bind(view)
        lifecycle.addObserver(viewModel)
        observe(viewModel.getViewState(), ::onViewState)
        viewModel.fetchPokemons()
    }

    private fun onViewState(state: ListViewState?) {
        when (state) {
            ListViewState.Loading -> {
                binding.llProgressBar.root.visibility = View.VISIBLE
                binding.llyMain.visibility = View.GONE
            }

            is ListViewState.ItemsSearch -> {
                binding.llProgressBar.root.visibility = View.GONE
                binding.llyMain.visibility = View.VISIBLE
                    setupRecyclerViewList()
                    lifecycleScope.launch {
                        pokemonAdapterList.submitData(state.pokemons)
                    }
            }

            is ListViewState.ErrorLoadingItems -> {
                binding.llProgressBar.root.visibility = View.GONE
                binding.llyMain.visibility = View.VISIBLE
            }

            else -> {

            }
        }
    }

    private fun setupRecyclerViewList() {
        pokemonAdapterList =
            PokemonsAdapterList(this)
        val linearLayoutManager = LinearLayoutManager(context)
        binding.rcvRow.layoutManager = linearLayoutManager
        binding.rcvRow.adapter = pokemonAdapterList
    }


    override fun onClick(pokemonItem: PokemonModel) {
    }
}