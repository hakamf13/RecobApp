package com.manpro.recobapp.ui.bottomnav.notification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manpro.recobapp.R
import com.manpro.recobapp.databinding.ActivityNotificationBinding
import com.manpro.recobapp.ui.bottomnav.home.HomeActivity
import com.manpro.recobapp.ui.bottomnav.profile.ProfileActivity
import com.manpro.recobapp.ui.bottomnav.shop.ShopActivity

class NotificationActivity : AppCompatActivity() {

    private val binding: ActivityNotificationBinding by lazy {
        ActivityNotificationBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        /*Bottom Menu Navigation*/
        binding.bottomMenu.menuShop.setOnClickListener {
            startActivity(
                Intent(
                this@NotificationActivity,
                ShopActivity::class.java)
            )
        }

        binding.bottomMenu.menuProfile.setOnClickListener {
            startActivity(
                Intent(
                this@NotificationActivity,
                ProfileActivity::class.java)
            )
        }

        binding.bottomMenu.menuHome.setOnClickListener {
            startActivity(
                Intent(
                this@NotificationActivity,
                HomeActivity::class.java)
            )
        }
    }
}