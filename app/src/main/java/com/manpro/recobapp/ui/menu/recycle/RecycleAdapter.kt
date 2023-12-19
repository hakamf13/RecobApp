package com.manpro.recobapp.ui.menu.recycle

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manpro.recobapp.data.local.model.recycle.RecycleModel
import com.manpro.recobapp.data.network.response.product.Sampah
import com.manpro.recobapp.databinding.ItemListRecycleBinding
import com.manpro.recobapp.ui.menu.recycle.checkout.CartActivity

class RecycleAdapter(
    private val listItem: ArrayList<Sampah>,
    private val context: Context
) : RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder>() {

    var onItemClick: ((Sampah) -> Unit)? = null

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
            .load(item.image)
            .centerCrop()
            .into(holder.ivPhoto)
        holder.apply {
            tvTitle.text = item?.name
            tvValue.text = "${item?.reward} Poin/Kg"
        }

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(listItem[position])
            val intent = Intent(holder.itemView.context, CartActivity::class.java)
            intent.putExtra("product", item)
            holder.itemView.context.startActivity(intent)
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