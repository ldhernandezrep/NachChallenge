package com.example.nacchallenge.listpokemon

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nacchallenge.R
import com.example.nacchallenge.databinding.FragmentListPokemonBinding
import com.example.nacchallenge.listpokemon.adapters.PokemonLoadStateAdapter
import com.example.nacchallenge.listpokemon.adapters.PokemonsAdapterList
import com.example.nacchallenge.listpokemon.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListPokemonFragment : Fragment(R.layout.fragment_list_pokemon), IClickPokemonListener {

    private lateinit var binding: FragmentListPokemonBinding
    private lateinit var pokemonAdapterList: PokemonsAdapterList
    private val viewModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListPokemonBinding.bind(view)
        lifecycle.addObserver(viewModel)

        pokemonAdapterList =
            PokemonsAdapterList(this)
        val linearLayoutManager = LinearLayoutManager(context)
        binding.rcvRow.layoutManager = linearLayoutManager
        binding.rcvRow.adapter = pokemonAdapterList

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pokemonList.collectLatest { pagingData ->
                pokemonAdapterList.submitData(pagingData)
            }
        }


        binding.rcvRow.adapter = pokemonAdapterList.withLoadStateHeaderAndFooter(
            header = PokemonLoadStateAdapter { pokemonAdapterList.retry() },
            footer = PokemonLoadStateAdapter { pokemonAdapterList.retry() }
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.pokemonList.collectLatest { pagingData ->
                pokemonAdapterList.submitData(pagingData)
            }
        }

        pokemonAdapterList.addLoadStateListener { combinedLoadStates ->
            val isListEmpty =
                combinedLoadStates.refresh is LoadState.NotLoading && pokemonAdapterList.itemCount == 0
            // Puedes manejar el estado de carga y errores según tu lógica aquí
            // Por ejemplo, mostrar u ocultar vistas de carga y errores en tu fragmento.
        }

        /*observe(viewModel.getViewState(), ::onViewState)
        viewModel.fetchPokemons()*/
    }

   /* private fun onViewState(state: ListViewState?) {
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
    }*/

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_principal, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.location -> {
                findNavController().navigate(
                    R.id.action_listPokemonFragment2_to_location_nav
                )
                requireActivity().invalidateOptionsMenu()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerViewList() {
        pokemonAdapterList =
            PokemonsAdapterList(this)
        val linearLayoutManager = LinearLayoutManager(context)
        binding.rcvRow.layoutManager = linearLayoutManager
        binding.rcvRow.adapter = pokemonAdapterList
    }


    override fun onClick(pokemonItem: com.models.lpokemon.model.PokemonModel) {
        findNavController().navigate(
            R.id.action_listPokemonFragment2_to_detailPokemonFragment,
            bundleOf(
                "name_pokemon" to pokemonItem.name
            )
        )
    }
}