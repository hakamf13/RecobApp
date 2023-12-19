package com.manpro.recobapp.ui.menu.recycle

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.manpro.recobapp.R
import com.manpro.recobapp.data.local.model.recycle.RecycleModel
import com.manpro.recobapp.databinding.ActivityRecycleBinding
import com.manpro.recobapp.ui.bottomnav.home.HomeActivity
import com.manpro.recobapp.ui.menu.recycle.dummy.DummyRecycleAdapter
import com.manpro.recobapp.utils.LoadingBar

class RecycleActivity : AppCompatActivity() {

    private val binding: ActivityRecycleBinding by lazy {
        ActivityRecycleBinding.inflate(layoutInflater)
    }

    private lateinit var rvRecycle: RecyclerView
    private lateinit var vm: RecycleViewModel
    private val list = ArrayList<RecycleModel>()

//    private var counter: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        rvRecycle = findViewById(R.id.rvBarangCo)
        rvRecycle.setHasFixedSize(true)

        list.addAll(listItem)
        showRecylerList()

        setSearchView()
//        setKeranjangButton()
        setListener()

    }

    private val listItem: ArrayList<RecycleModel>
        @SuppressLint("Recycle")
        get() {
            val dataName = resources.getStringArray(R.array.item_name)
            val dataValue = resources.getStringArray(R.array.item_value)
            val dataPhoto = resources.obtainTypedArray(R.array.item_photo)
            val listItem = ArrayList<RecycleModel>()
            for (i in dataName.indices) {
                val items = RecycleModel(dataName[i], dataValue[i], dataPhoto.getResourceId(i, -1))
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
                openDialog()
            }
        })
    }

    private fun showSelectedItems(item: RecycleModel) {
        Toast.makeText(this, "Kamu memilih " + item.name, Toast.LENGTH_SHORT).show()
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

/*    private fun setKeranjangButton() {
        counter = 0
        val btnCart = binding.btnCartSticky
        btnCart.setOnClickListener {
            openDialog()
        }
    }*/

    private fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.popup_recycle)
        val item = listItem

        dialog.findViewById<TextView>(R.id.tv_titleRecyclePhoto)

        dialog.show()
    }

    private fun observeQuantity() {

    }

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
}