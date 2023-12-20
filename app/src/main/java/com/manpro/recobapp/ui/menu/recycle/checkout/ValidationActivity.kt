package com.manpro.recobapp.ui.menu.recycle.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manpro.recobapp.R
import com.manpro.recobapp.data.local.model.recycle.RecycleModel
import com.manpro.recobapp.databinding.ActivityValidationBinding
import com.manpro.recobapp.ui.bottomnav.home.HomeActivity

@Suppress("DEPRECATION")
class ValidationActivity : AppCompatActivity() {

    private val binding: ActivityValidationBinding by lazy {
        ActivityValidationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        val dataReceive = intent.getParcelableExtra<RecycleModel>(DATAVAL)

        binding.kembaliBeranda.setOnClickListener {
            val dataIntent = Intent(this@ValidationActivity,
                HomeActivity::class.java
            )
            dataIntent.putExtra(HomeActivity.POIN, dataReceive?.totalPoint?.toInt() ?: 0)
            startActivity(dataIntent)
        }


    }

    companion object {
        const val DATAVAL = "dataval"
    }
}