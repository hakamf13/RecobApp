package com.manpro.recobapp.ui.menu.recycle

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manpro.recobapp.R
import com.manpro.recobapp.data.network.response.product.Sampah
import com.manpro.recobapp.ui.menu.recycle.checkout.CartActivity

class RecyclePagingAdapter : PagingDataAdapter<Sampah, RecyclePagingAdapter.ListViewHolder>(
    DIFF_CALLBACK
) {

    var onItemClick : ((Sampah) -> Unit)? = null

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Sampah>() {
            override fun areItemsTheSame(oldItem: Sampah, newItem: Sampah): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Sampah, newItem: Sampah): Boolean {
                return oldItem.barangId == newItem.barangId
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_list_recycle, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            Glide.with(holder.itemView.context)
                .load(item.image)
                .centerCrop()
                .into(holder.ivPhoto)
        }

        holder.tvTitle.text = item?.name

        holder.itemView.setOnClickListener {
            getItem(position)?.let { it1 -> onItemClick?.invoke(it1) }
            val intent = Intent(holder.itemView.context, CartActivity::class.java)
            intent.putExtra("item", item)
            holder.itemView.context.startActivity(intent)
        }

        holder.tvValue.text = "${item?.reward} Poin/Kg"
    }

    class ListViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.findViewById(R.id.iv_recyclePhoto)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_titleRecyclePhoto)
        val tvValue: TextView = itemView.findViewById(R.id.tv_valueRecyclePhoto)
    }

}