package com.example.nacchallenge.detailpokemon

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nacchallenge.commons.BaseViewHolder
import com.example.nacchallenge.databinding.ItemAbilityBinding

class DetailsAdapter(val listDetails: List<String>) : RecyclerView.Adapter<BaseViewHolder<*>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ItemAbilityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsAdapterViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is DetailsAdapter.DetailsAdapterViewHolder -> holder.bind(listDetails[position])
        }
    }

    override fun getItemCount(): Int = listDetails.size

    inner class DetailsAdapterViewHolder(
        var binding: ItemAbilityBinding,
        var context: Context
    ) :
        BaseViewHolder<String>(binding.root) {

        override fun bind(item: String) {
            binding.tvText.text = item
        }
    }

}