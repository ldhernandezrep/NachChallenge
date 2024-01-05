package com.example.nacchallenge.listpokemon.adapters

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.nacchallenge.listpokemon.PokemonLoadStateViewHolder

class PokemonLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PokemonLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PokemonLoadStateViewHolder {
        return PokemonLoadStateViewHolder.create(parent, retry)
    }

    override fun onBindViewHolder(
        holder: PokemonLoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }
}