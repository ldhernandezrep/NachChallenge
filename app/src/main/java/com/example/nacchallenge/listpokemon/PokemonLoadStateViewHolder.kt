package com.example.nacchallenge.listpokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.nacchallenge.databinding.ItemLoadStateBinding

class PokemonLoadStateViewHolder(
    private val binding: ItemLoadStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = (loadState as? LoadState.Error)?.error != null
        binding.errorMsg.text = (loadState as? LoadState.Error)?.error?.localizedMessage
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PokemonLoadStateViewHolder {
            val binding = ItemLoadStateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return PokemonLoadStateViewHolder(binding, retry)
        }
    }
}
