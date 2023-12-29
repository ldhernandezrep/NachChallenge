package com.example.nacchallenge.listpokemon

import PokemonModel
import androidx.recyclerview.widget.DiffUtil

class Comparator {
    companion object {
       public val PokemonComparator = object : DiffUtil.ItemCallback<PokemonModel>() {
            override fun areItemsTheSame(
                oldItem: PokemonModel,
                newItem: PokemonModel
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: PokemonModel,
                newItem: PokemonModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}