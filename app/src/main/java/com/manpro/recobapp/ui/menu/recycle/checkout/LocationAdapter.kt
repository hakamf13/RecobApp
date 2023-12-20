package com.manpro.recobapp.ui.menu.recycle.checkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manpro.recobapp.databinding.ItemListLocationBinding

class LocationAdapter(private val listItem: ArrayList<LocationModel>) : RecyclerView.Adapter<LocationAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemListLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, address) = listItem[position]
        holder.binding.tvTitleLocation.text = name
        holder.binding.tvAddressLocation.text = address

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(
                listItem[holder.absoluteAdapterPosition]
            )
        }
    }

    override fun getItemCount(): Int = listItem.size

    class ListViewHolder(var binding: ItemListLocationBinding): RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: LocationModel)
    }
}