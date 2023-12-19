package com.manpro.recobapp.ui.bottomnav.shop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manpro.recobapp.R
import com.manpro.recobapp.databinding.ActivityShopBinding
import com.manpro.recobapp.ui.bottomnav.home.HomeActivity
import com.manpro.recobapp.ui.bottomnav.notification.NotificationActivity
import com.manpro.recobapp.ui.bottomnav.profile.ProfileActivity

class ShopActivity : AppCompatActivity() {

    private val binding: ActivityShopBinding by lazy {
        ActivityShopBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        /*Bottom Menu Navigation*/
        binding.bottomMenu.menuProfile.setOnClickListener {
            startActivity(
                Intent(
                this@ShopActivity,
                ProfileActivity::class.java)
            )
        }

        binding.bottomMenu.menuNotice.setOnClickListener {
            startActivity(
                Intent(
                this@ShopActivity,
                NotificationActivity::class.java)
            )
        }

        binding.bottomMenu.menuHome.setOnClickListener {
            startActivity(
                Intent(
                this@ShopActivity,
                HomeActivity::class.java)
            )
        }
    }
}