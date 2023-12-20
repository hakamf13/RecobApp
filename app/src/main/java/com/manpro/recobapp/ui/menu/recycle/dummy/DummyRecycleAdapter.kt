package com.manpro.recobapp.ui.menu.recycle.dummy

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manpro.recobapp.data.local.model.recycle.RecycleModel
import com.manpro.recobapp.databinding.ItemListRecycleBinding
import com.manpro.recobapp.ui.menu.recycle.checkout.CartModel

class DummyRecycleAdapter(private val listItem: ArrayList<RecycleModel>) : RecyclerView.Adapter<DummyRecycleAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListViewHolder {
        val binding = ItemListRecycleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photoUrl, name, quantity, value, point, totalPoint) = listItem[position]
        holder.binding.tvTitleRecyclePhoto.text = name
        holder.binding.tvValueRecyclePhoto.text = "$value Poin/Kg"

        Glide.with(holder.itemView.context)
            .load(photoUrl)
            .centerCrop()
            .into(holder.binding.ivRecyclePhoto)

        holder.itemView.setOnClickListener {
            if (::onItemClickCallback.isInitialized) {
                onItemClickCallback.onItemClicked(
                    listItem[holder.absoluteAdapterPosition]
                )
            }
        }
    }

    override fun getItemCount(): Int = listItem.size

    class ListViewHolder(var binding: ItemListRecycleBinding): RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: RecycleModel)
    }

}