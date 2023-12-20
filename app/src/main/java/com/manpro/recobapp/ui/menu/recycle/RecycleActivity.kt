package com.manpro.recobapp.ui.menu.recycle

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manpro.recobapp.R
import com.manpro.recobapp.data.local.model.recycle.RecycleModel
import com.manpro.recobapp.databinding.ActivityRecycleBinding
import com.manpro.recobapp.ui.bottomnav.home.HomeActivity
import com.manpro.recobapp.ui.menu.recycle.checkout.CartActivity
import com.manpro.recobapp.ui.menu.recycle.checkout.CartModel
import com.manpro.recobapp.ui.menu.recycle.checkout.LocationActivity
import com.manpro.recobapp.ui.menu.recycle.dummy.DummyRecycleAdapter
import com.manpro.recobapp.ui.welcome.splash.dataStore
import com.manpro.recobapp.utils.SessionPreference

class RecycleActivity : AppCompatActivity() {

    private val binding: ActivityRecycleBinding by lazy {
        ActivityRecycleBinding.inflate(layoutInflater)
    }

    private lateinit var vm: RecycleViewModel

    private lateinit var rvRecycle: RecyclerView
    private val list = ArrayList<RecycleModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        val pref = SessionPreference.getInstance(dataStore)
        vm = ViewModelProvider(this, ViewModelFactory(pref))[RecycleViewModel::class.java]

        rvRecycle = findViewById(R.id.rvBarangCo)
        rvRecycle.setHasFixedSize(true)

        list.addAll(listItem)
        showRecylerList()

        setSearchView()
        setListener()

        /*binding.btnCartSticky.setOnClickListener {
            val dataIntent = Intent(this, LocationActivity::class.java)
            dataIntent.putExtra(CartActivity.ITEMS, listItem)
            startActivity(dataIntent)
        }*/

        binding.btnCartSticky.setOnClickListener {
            val dataIntent = Intent(this@RecycleActivity, LocationActivity::class.java)
            dataIntent.putExtra(LocationActivity.DATAS, listItem)
            startActivity(dataIntent)
        }

    }

    private val listItem: ArrayList<RecycleModel>
        @SuppressLint("Recycle")
        get() {
            val dataPhoto = resources.obtainTypedArray(R.array.item_photo)
            val dataName = resources.getStringArray(R.array.item_name)
            val dataQty = resources.getStringArray(R.array.item_qty)
            val dataValue = resources.getStringArray(R.array.item_value)
            val dataPoint = resources.getStringArray(R.array.item_poin)
            val totalPoint = resources.getStringArray(R.array.item_totalPoin)
            val listItem = ArrayList<RecycleModel>()
            for (i in dataName.indices) {
                val items = RecycleModel(
                    dataPhoto.getResourceId(i, -1),
                    dataName[i],
                    dataQty[i],
                    dataValue[i],
                    dataPoint[i],
                    totalPoint[i]
                    )
                listItem.add(items)
            }
            return listItem
        }

    private fun showRecylerList() {
        rvRecycle.layoutManager = GridLayoutManager(this, 2)
        val listItems = DummyRecycleAdapter(list)
        rvRecycle.adapter = listItems

        listItems.setOnItemClickCallback(object : DummyRecycleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: RecycleModel) {
                showSelectedItems(data)
                openDialog(data)
            }
        })
    }

    private fun showSelectedItems(item: RecycleModel) {
        Toast.makeText(this, "Kamu memilih " + item.nama, Toast.LENGTH_SHORT).show()
    }

    private fun setListener() {
        binding.apply {

            backNav.setOnClickListener {
                startActivity(Intent(
                    this@RecycleActivity,
                    HomeActivity::class.java
                ))
            }

        }
    }

    private fun openDialog(item: RecycleModel) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.popup_recycle)

        val ivRecyclePhoto = dialog.findViewById<ImageView>(R.id.iv_recyclePhoto)
        Glide.with(this)
            .load(item.photoUrl)
            .centerCrop()
            .into(ivRecyclePhoto)

        dialog.findViewById<TextView>(R.id.tv_titleRecyclePhoto).text = item.nama
        dialog.findViewById<TextView>(R.id.tv_valueRecyclePhoto).text = "${item.value} Poin/Kg"
        dialog.findViewById<EditText>(R.id.etNumberRecycle).setText(item.quantity)

        dialog.findViewById<Button>(R.id.btnSubmitNumberRecycle).setOnClickListener {

            /*val cartItem = RecycleModel(
                photoUrl = item.photoUrl,
                nama = item.nama,
                quantity = item.quantity,
                value = item.value,
                point = item.point,
                totalPoint = item.totalPoint
            )*/

            val itemPoin = calculatePoint(item)
            vm.updatePoinAfterAddItem(itemPoin)

            val dataIntent = Intent(this@RecycleActivity, LocationActivity::class.java)
            dataIntent.putExtra(LocationActivity.DATAS, item)
            startActivity(dataIntent)

            /*binding.btnCartSticky.setOnClickListener {
                val dataIntent = Intent(this@RecycleActivity, LocationActivity::class.java)
                dataIntent.putExtra(LocationActivity.DATAS, data)
                startActivity(dataIntent)
            }*/

            /*val intent = Intent(this, CartActivity::class.java).apply {
                putExtra("photoUrl", item.photoUrl)
                putExtra("nama", item.name)
                putExtra("quantity", dialog.findViewById<EditText>(R.id.etNumberRecycle).text.toString().toInt())
                putExtra("value", item.value.toInt())
                putExtra("point", calculatePoint(item))
                putExtra("totalPoint", calculatePoint(item) * dialog.findViewById<EditText>(R.id.etNumberRecycle).text.toString().toInt())
            }
            intent.putExtra(CartActivity.ITEMS, item)
            startActivity(intent)*/

            val listItems = DummyRecycleAdapter(list)
            rvRecycle.adapter = listItems

            /*cartItems.add(cartItem)
            listItems.notifyDataSetChanged()*/

            Toast.makeText(this, "Berhasil menambahkan " + item.nama, Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        val layoutParams = dialog.window?.attributes
        layoutParams?.dimAmount = 0.7f
        dialog.window?.attributes = layoutParams
        dialog.show()
    }

    private fun calculatePoint(item: RecycleModel): Int {
        return item.value.toInt() * item.quantity.toInt()
    }

    /*private fun calculatePoint(item: RecycleModel): Int {
        return item.value.toInt() * item.quantity.toInt()
    }*/


    private fun setSearchView() {
        val tilCariSampah = binding.tilCariSampah
        tilCariSampah.boxStrokeColor = Color.parseColor("#049E87")
        tilCariSampah.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                tilCariSampah.boxStrokeColor = Color.parseColor("#C3C3C3")
            } else {
                tilCariSampah.boxStrokeColor = Color.parseColor("#049E87")
            }
        }
    }

    companion object {
        val cartItems = ArrayList<CartModel>()
        const val ITEM = "item"
    }
}