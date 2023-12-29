package com.example.nacchallenge.detailpokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.usecases.pokemon.model.PokemonDetail
import com.example.nacchallenge.R
import com.example.nacchallenge.commons.loadImageUrl
import com.example.nacchallenge.databinding.FragmentDetailPokemonBinding
import com.example.nacchallenge.detailpokemon.viewmodels.DetailPokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import observe


private const val ARG_PARAM1 = "name_pokemon"

@AndroidEntryPoint
class DetailPokemonFragment : Fragment(R.layout.fragment_detail_pokemon) {
    // TODO: Rename and change types of parameters
    private var namePokemon: String? = null

    private lateinit var binding: FragmentDetailPokemonBinding
    private val viewModel: DetailPokemonViewModel by viewModels()
    private var pokemonDetail: PokemonDetail? = null
    private lateinit var pokemonDetailAdapter: DetailsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            namePokemon = it.getString(ARG_PARAM1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailPokemonBinding.bind(view)
        val activity = activity as AppCompatActivity?
        activity?.supportActionBar?.hide()
        lifecycle.addObserver(viewModel)
        observe(viewModel.getViewState(), ::onViewState)
        viewModel.fetchPokemon(namePokemon!!)
    }

    private fun onViewState(state: DetailViewState?) {
        when (state) {
            DetailViewState.Loading -> {
                binding.llyMainDetail.visibility = View.GONE
                binding.llProgressBar.root.visibility = View.VISIBLE
                //requireActivity().toast("Error al cargar los productos")
            }

            is DetailViewState.ItemsLoaded -> {
                binding.llyMainDetail.visibility = View.VISIBLE
                binding.llProgressBar.root.visibility = View.GONE
                pokemonDetail = state.pokemon
                llenarView()
            }

            DetailViewState.ErrorLoadingItems -> {
                binding.llyMainDetail.visibility = View.GONE
                binding.llProgressBar.root.visibility = View.VISIBLE
                //requireActivity().toast("Error al actualizar el producto")
            }

            else -> {

            }
        }
    }

    private fun setUpRecycler(list: List<String>)
    {
        pokemonDetailAdapter =
            DetailsAdapter(list)
        val linearLayoutManager = LinearLayoutManager(context)
        binding.rcvAbilities.layoutManager = linearLayoutManager
        binding.rcvAbilities.adapter = pokemonDetailAdapter
    }

    private fun llenarView() {
        setUpRecycler(pokemonDetail!!.abilities.abilities.map { it.ability.name })
        binding.tvNamePokemonDetail.text = pokemonDetail!!.name.uppercase()
        binding.imageView.loadImageUrl(url = pokemonDetail!!.imagePokemon.back_default,nameImage = pokemonDetail!!.name , context =  requireContext())
        binding.tvWeightPokemonDetail.text = pokemonDetail!!.weight.toString() + " Kg"
        binding.tvHeightPokemonDetail.text = pokemonDetail!!.height.toString() + " Cm"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val activity = activity as AppCompatActivity?
        activity?.supportActionBar?.show()
    }

}