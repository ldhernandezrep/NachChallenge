package com.example.mylocations

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.locationmodel.Location
import com.example.mylocations.common.BaseViewHolder
import com.example.mylocations.databinding.ItemLocationBinding

class LocationAdapter(val locations: List<Location>) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationAdapterViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is LocationAdapter.LocationAdapterViewHolder -> holder.bind(locations[position])
        }
    }

    override fun getItemCount(): Int = locations.size

    inner class LocationAdapterViewHolder(
        var binding: ItemLocationBinding,
        var context: Context
    ) :
        BaseViewHolder<Location>(binding.root) {

        override fun bind(item: Location) {
            binding.textLatitude.text = "Latitud:" + item.latitiud.toString()
            binding.textLongitude.text = "Longitud:" +  item.longitud.toString()
        }
    }

}