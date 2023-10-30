package com.manpro.recobapp.ui.menu.recycle

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manpro.recobapp.data.local.model.recycle.RecycleModel
import com.manpro.recobapp.databinding.ItemListRecycleBinding

class RecycleAdapter(
    private val listItem: List<RecycleModel>,
    private val context: Context
) : RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewHolder {
        val binding = ItemListRecycleBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return RecycleViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecycleViewHolder, position: Int) {
        val item = listItem[position]
        Glide.with(holder.itemView.context)
            .load(item.photoUrl)
            .centerCrop()
            .into(holder.ivPhoto)
        holder.apply {
            tvTitle.text = item.name
            tvValue.text = "${item.value} Poin/Kg"
        }
    }

    override fun getItemCount(): Int = listItem.size

    class RecycleViewHolder(private val binding: ItemListRecycleBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val ivPhoto: ImageView = binding.ivRecyclePhoto
        val tvTitle: TextView = binding.tvTitleRecyclePhoto
        val tvValue: TextView = binding.tvValueRecyclePhoto

    }

}