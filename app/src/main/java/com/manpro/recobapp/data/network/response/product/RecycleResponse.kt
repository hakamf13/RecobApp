package com.manpro.recobapp.data.network.response.product

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class RecycleResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("statusCode")
	val statusCode: Int
)

data class User(

	@field:SerializedName("poin")
	val poin: Int,

	@field:SerializedName("image")
	val image: String,

	@field:SerializedName("createdAt")
	val createdAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("long")
	val jsonMemberLong: Any,

	@field:SerializedName("lat")
	val lat: Any,

	@field:SerializedName("alamat")
	val alamat: Any,

	@field:SerializedName("updatedAt")
	val updatedAt: String
)

@Parcelize
data class Sampah(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("reward")
	val reward: Int,

	@field:SerializedName("image")
	val image: Int,

	@field:SerializedName("barang_id")
	val barangId: String,

) : Parcelable

data class CartItem(

	@field:SerializedName("cart_id")
	val cartId: Int,

	@field:SerializedName("quantity")
	val quantity: Int,

	@field:SerializedName("subtotal")
	val subtotal: String,

	@field:SerializedName("sampah")
	val sampah: Sampah
)

data class Data(

	@field:SerializedName("user")
	val user: User,

	@field:SerializedName("cart")
	val cart: List<CartItem>
)
