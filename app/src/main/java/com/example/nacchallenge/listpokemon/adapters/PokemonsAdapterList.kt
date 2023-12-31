package com.example.nacchallenge.listpokemon.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.models.lpokemon.model.PokemonModel
import com.example.nacchallenge.commons.BaseViewHolder
import com.example.nacchallenge.commons.loadImageUrl
import com.example.nacchallenge.databinding.ItemRowBinding
import com.example.nacchallenge.listpokemon.Comparator.Companion.PokemonComparator
import com.example.nacchallenge.listpokemon.IClickPokemonListener

class PokemonsAdapterList(val iClickPokemonListener: IClickPokemonListener) : PagingDataAdapter<com.models.lpokemon.model.PokemonModel, PokemonsAdapterList.PokemonListViewHolder>(PokemonComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val itemBinding =
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonListViewHolder(itemBinding, parent.context);
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val pokemon = getItem(position)
        when (holder) {
            is PokemonListViewHolder -> pokemon?.let { holder.bind(it) }
        }
    }


    inner class PokemonListViewHolder(var binding: ItemRowBinding, var context: Context) :
        BaseViewHolder<com.models.lpokemon.model.PokemonModel>(binding.root) {

        override fun bind(item: com.models.lpokemon.model.PokemonModel) {
            binding.tvNombrePokemonRow.text = item.name.uppercase()
            binding.tvNumeroPokemon.text = "# ${item.number.toString()}"
            binding.lnyRow.setOnClickListener {
                iClickPokemonListener.onClick(item)
            }
            when(item.number)
            {
                1 -> {
                    binding.imPokemonRow.loadImageUrl("", item.name, context)
                }
                4 -> {
                    binding.imPokemonRow.loadImageUrl("", "", context)
                }
                else ->
                {
                    binding.imPokemonRow.loadImageUrl(item.url, item.name, context)
                }
            }


        }
    }

}