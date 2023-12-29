package com.example.nacchallenge.listpokemon.adapters

import IClickPokemonListener
import PokemonModel
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.nacchallenge.commons.BaseViewHolder
import com.example.nacchallenge.commons.loadImageUrl
import com.example.nacchallenge.databinding.ItemRowBinding
import com.example.nacchallenge.listpokemon.Comparator.Companion.PokemonComparator

class PokemonsAdapterList(val iClickPokemonListener: IClickPokemonListener) : PagingDataAdapter<PokemonModel, PokemonsAdapterList.PokemonListViewHolder>(PokemonComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder{
        val itemBinding =
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = PokemonListViewHolder(itemBinding, parent.context)
        return holder;
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val pokemon = getItem(position)
        when (holder) {
            is PokemonListViewHolder -> pokemon?.let { holder.bind(it) }
        }
    }


    inner class PokemonListViewHolder(var binding: ItemRowBinding, var context: Context) :
        BaseViewHolder<PokemonModel>(binding.root) {

        override fun bind(item: PokemonModel) {
            binding.tvNombrePokemonRow.text = item.name.uppercase()
            binding.tvNumeroPokemon.text = "# ${item.number.toString()}"
            binding.lnyRow.setOnClickListener {
                iClickPokemonListener.onClick(item)
            }
            binding.imPokemonRow.loadImageUrl(item.url, item.name, context)

        }
    }

}