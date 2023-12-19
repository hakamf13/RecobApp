package com.manpro.recobapp.ui.bottomnav.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.manpro.recobapp.R
import com.manpro.recobapp.databinding.ActivityProfileBinding
import com.manpro.recobapp.ui.bottomnav.home.HomeActivity
import com.manpro.recobapp.ui.bottomnav.notification.NotificationActivity
import com.manpro.recobapp.ui.bottomnav.shop.ShopActivity
import com.manpro.recobapp.ui.welcome.auth.login.LoginActivity
import com.manpro.recobapp.ui.welcome.splash.dataStore
import com.manpro.recobapp.utils.SessionPreference

class ProfileActivity : AppCompatActivity() {

    private val binding: ActivityProfileBinding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    private lateinit var vm: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        val pref = SessionPreference.getInstance(dataStore)
        vm = ViewModelProvider(this, ViewModelFactory(pref))[ProfileViewModel::class.java]

        /*var status = false
        binding.*/

        binding.logoutProfile.setOnClickListener {
            vm.clearToken()
            finish()
            startActivity(Intent(
                this@ProfileActivity,
                LoginActivity::class.java
            ))
        }

        /*Bottom Menu Navigation*/
        binding.bottomMenu.menuShop.setOnClickListener {
            startActivity(Intent(
                this@ProfileActivity,
                ShopActivity::class.java)
            )
        }

        binding.bottomMenu.menuNotice.setOnClickListener {
            startActivity(Intent(
                this@ProfileActivity,
                NotificationActivity::class.java)
            )
        }

        binding.bottomMenu.menuHome.setOnClickListener {
            startActivity(Intent(
                this@ProfileActivity,
                HomeActivity::class.java)
            )
        }
    }
}