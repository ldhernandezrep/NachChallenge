package com.example.nacchallenge.listpokemon

import androidx.recyclerview.widget.DiffUtil
import com.models.lpokemon.model.PokemonModel

class Comparator {
    companion object {
       public val PokemonComparator = object : DiffUtil.ItemCallback<com.models.lpokemon.model.PokemonModel>() {
            override fun areItemsTheSame(
                oldItem: com.models.lpokemon.model.PokemonModel,
                newItem: com.models.lpokemon.model.PokemonModel
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: com.models.lpokemon.model.PokemonModel,
                newItem: com.models.lpokemon.model.PokemonModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}