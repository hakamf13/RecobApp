package com.manpro.recobapp.ui.menu.recycle.checkout

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manpro.recobapp.databinding.ItemListCartBinding

class CartAdapter(private val cartItems: ArrayList<CartModel>) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemListCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val (photoUrl, name, quantity, value, point, totalPoint) = cartItems[position]

        Glide.with(holder.itemView.context)
            .load(photoUrl)
            .centerCrop()
            .into(holder.binding.ivBarangCo)

        holder.binding.namaBarangCo.text = name
        holder.binding.valueJumlahBarangCo.text = "$quantity Kg"
        holder.binding.valueHargaBarangCo.text = "$value Poin/Kg"
        holder.binding.valuePoinBarangCo.text = "$point Poin"
        holder.binding.totalPoinCo.text = "$totalPoint Poin"

        holder.itemView.setOnClickListener {
            if (::onItemClickCallback.isInitialized) {
                onItemClickCallback.onItemClicked(
                    cartItems[holder.absoluteAdapterPosition]
                )
            }
        }

    }

    /*@SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.binding.namaBarangCo.text = cartItem.nama
        holder.binding.valueJumlahBarangCo.text = "${cartItem.quantity} Kg"
        holder.binding.valueHargaBarangCo.text = "${cartItem.value} Poin/Kg"
        holder.binding.valuePoinBarangCo.text = "${cartItem.totalPoint} Poin"

        // Add other necessary bindings and listeners here
    }*/

    override fun getItemCount(): Int = cartItems.size

    class CartViewHolder(var binding: ItemListCartBinding): RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: CartModel)
    }

}