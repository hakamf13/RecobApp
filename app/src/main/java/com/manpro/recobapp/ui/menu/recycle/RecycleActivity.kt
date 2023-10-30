package com.manpro.recobapp.ui.menu.recycle

import android.app.Dialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manpro.recobapp.R
import com.manpro.recobapp.databinding.ActivityRecycleBinding

class RecycleActivity : AppCompatActivity() {

    private val binding: ActivityRecycleBinding by lazy {
        ActivityRecycleBinding.inflate(layoutInflater)
    }

    private var counter: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSearchView()
        setKeranjangButton()

    }

    private fun setKeranjangButton() {
        counter = 0
        val btnCart = binding.btnCartSticky
        btnCart.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.popup_recycle)

        // Ambil data barang yang dipilih user
//        val barang =
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