package com.manpro.recobapp.ui.menu.recycle.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manpro.recobapp.R
import com.manpro.recobapp.data.local.model.recycle.RecycleModel
import com.manpro.recobapp.databinding.ActivityLocationBinding
import com.manpro.recobapp.ui.bottomnav.home.HomeActivity
import com.manpro.recobapp.ui.menu.recycle.RecycleActivity

@Suppress("DEPRECATION")
class LocationActivity : AppCompatActivity() {

    private val binding: ActivityLocationBinding by lazy {
        ActivityLocationBinding.inflate(layoutInflater)
    }

    private lateinit var rvLocation: RecyclerView
    private val list = ArrayList<LocationModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        rvLocation = findViewById(R.id.rvBankLocation)
        rvLocation.setHasFixedSize(true)

        list.addAll(listLocation)
        showRecyclerList()

        setListener()

    }

    private val listLocation: ArrayList<LocationModel>
        get() {
            val dataName = resources.getStringArray(R.array.data_lokasi)
            val dataAddress = resources.getStringArray(R.array.data_lokasi_lengkap)
            val listLocation = ArrayList<LocationModel>()
            for (i in dataName.indices) {
                val items = LocationModel(dataName[i], dataAddress[i])
                listLocation.add(items)
            }
            return listLocation
        }

    private fun showRecyclerList() {
        rvLocation.layoutManager = LinearLayoutManager(this)
        val listItems = LocationAdapter(list)
        rvLocation.adapter = listItems

        listItems.setOnItemClickCallback(object : LocationAdapter.OnItemClickCallback {
            override fun onItemClicked(data: LocationModel) {
                showSelectedItems(data)
                val dataReceive = intent.getParcelableExtra<RecycleModel>(RecycleActivity.ITEM)
                val dataLocationIntent = Intent(
                    this@LocationActivity,
                    CartActivity::class.java
                )
                dataLocationIntent.putExtra(CartActivity.LOCATION, data)
                dataLocationIntent.putExtra(CartActivity.ITEMS, dataReceive)
                startActivity(dataLocationIntent)
            }
        })
    }

    private fun showSelectedItems(item: LocationModel) {
        Toast.makeText(this, "Kamu memilih " + item.name, Toast.LENGTH_SHORT).show()
    }

    private fun setListener() {
        binding.apply {

            backNav.setOnClickListener {
                startActivity(
                    Intent(
                    this@LocationActivity,
                    RecycleActivity::class.java
                )
                )
            }

        }
    }

    companion object {
        const val DATAS = "datas"
    }
}