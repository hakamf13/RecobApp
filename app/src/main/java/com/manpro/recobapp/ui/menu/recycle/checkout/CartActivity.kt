package com.manpro.recobapp.ui.menu.recycle.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manpro.recobapp.R
import com.manpro.recobapp.data.local.model.recycle.RecycleModel
import com.manpro.recobapp.databinding.ActivityCartBinding
import com.manpro.recobapp.ui.bottomnav.home.HomeActivity
import com.manpro.recobapp.ui.bottomnav.home.HomeDummyActivity
import com.manpro.recobapp.ui.menu.recycle.RecycleActivity.Companion.cartItems
import com.manpro.recobapp.utils.loadImage

@Suppress("DEPRECATION")
class CartActivity : AppCompatActivity() {

    private val binding: ActivityCartBinding by lazy {
        ActivityCartBinding.inflate(layoutInflater)
    }

    private lateinit var rvCart: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        val dataLocationContent = intent.getParcelableExtra<LocationModel?>(LOCATION)
        val dataItemContent = intent.getParcelableExtra<RecycleModel>(ITEMS)

        if (dataLocationContent != null) {
            binding.apply {
                tvTitleLocation.text = dataLocationContent.name
                tvAddressLocation.text = dataLocationContent.address
            }
        } else {
            /*binding.apply {
                tvTitleLocation.text = "Bank Sampah Induk Surabaya"
                tvAddressLocation.text = "Jl. Kalibokor II No.5 60283 Surabaya Jawa Timur"
            }

            Toast.makeText(this, "Data lokasi tidak valid", Toast.LENGTH_SHORT).show()*/
        }

        if (dataItemContent != null) {
            binding.apply {
                ivBarangCo.loadImage(dataItemContent.photoUrl)
                namaBarangCo.text = dataItemContent.nama
                valueJumlahBarangCo.text = dataItemContent.quantity
                valueHargaBarangCo.text = dataItemContent.value
                valuePoinBarangCo.text = dataItemContent.point
                tvValueHargaTotalCo.text = dataItemContent.totalPoint
            }
        } else {
            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
        }

        /*val cartItems = ArrayList<CartModel>()
        val nama = intent.getStringExtra("nama")
        val quantity = intent.getIntExtra("quantity", 0)
        val value = intent.getIntExtra("value", 0)
        val point = intent.getIntExtra("point", 0)
        val totalPoint = intent.getIntExtra("totalPoint", 0)

        val cartItem = nama?.let {
            CartModel(
                photoUrl = 0,
                nama = it,
                quantity = quantity,
                value = value,
                point = point,
                totalPoint = totalPoint
            )
        }

        if (cartItem != null) {
            cartItems.add(cartItem)
        }*/

        /*rvCart = binding.rvBarangCo*/
        /*rvCart.layoutManager = LinearLayoutManager(this)
        rvCart.setHasFixedSize(true)

        showCartList(cartItems)*/

        binding.btnCartSticky.setOnClickListener {
            val homeIntent = Intent(
                this@CartActivity,
                HomeActivity::class.java
            )
            homeIntent.putExtra(HomeActivity.POIN, dataItemContent?.totalPoint)

            startActivity(homeIntent)
        }

    }

    /*private fun showCartList(cartItems: ArrayList<CartModel>) {
        rvCart.layoutManager = LinearLayoutManager(this)
        val cartAdapter = CartAdapter(cartItems)
        rvCart.adapter = cartAdapter
    }*/

    companion object {
        const val ITEMS = "items"
        const val LOCATION = "location"
    }
}