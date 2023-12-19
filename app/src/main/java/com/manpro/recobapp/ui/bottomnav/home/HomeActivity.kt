package com.manpro.recobapp.ui.bottomnav.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manpro.recobapp.data.local.model.recycle.RecycleModel
import com.manpro.recobapp.databinding.ActivityHomeBinding
import com.manpro.recobapp.ui.bottomnav.notification.NotificationActivity
import com.manpro.recobapp.ui.bottomnav.profile.ProfileActivity
import com.manpro.recobapp.ui.bottomnav.shop.ShopActivity
import com.manpro.recobapp.ui.menu.recycle.RecycleActivity
import com.manpro.recobapp.ui.welcome.auth.login.LoginActivity
import com.manpro.recobapp.ui.welcome.auth.register.RegisterActivity

@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        val dataCartContent = intent.getParcelableExtra<RecycleModel>(POIN)

        if (dataCartContent != null) {
            binding.apply {
                tvValueTotalPt.text = dataCartContent.value
            }
        } else {
//            binding.tvValueTotalPt.text = "20000"
        }

        binding.cvMenu2Recycle.setOnClickListener {
            startActivity(Intent(
                this@HomeActivity,
                RecycleActivity::class.java
            ))
        }

        /*Bottom Menu Navigation*/
        binding.bottomMenu.menuShop.setOnClickListener {
            startActivity(Intent(
                this@HomeActivity,
                ShopActivity::class.java
            ))
        }

        binding.bottomMenu.menuNotice.setOnClickListener {
            startActivity(Intent(
                this@HomeActivity,
                NotificationActivity::class.java
            ))
        }

        binding.bottomMenu.menuProfile.setOnClickListener {
            startActivity(Intent(
                this@HomeActivity,
                ProfileActivity::class.java
            ))
        }

    }

    companion object {
        const val POIN = "poin"
    }
}