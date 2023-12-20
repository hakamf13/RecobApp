package com.manpro.recobapp.ui.bottomnav.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.manpro.recobapp.data.local.model.recycle.RecycleModel
import com.manpro.recobapp.databinding.ActivityHomeBinding
import com.manpro.recobapp.ui.bottomnav.notification.NotificationActivity
import com.manpro.recobapp.ui.bottomnav.profile.ProfileActivity
import com.manpro.recobapp.ui.bottomnav.shop.ShopActivity
import com.manpro.recobapp.ui.menu.recycle.RecycleActivity
import com.manpro.recobapp.ui.welcome.auth.login.LoginActivity
import com.manpro.recobapp.ui.welcome.auth.register.RegisterActivity
import com.manpro.recobapp.ui.welcome.splash.dataStore
import com.manpro.recobapp.utils.SessionPreference

@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private lateinit var vm: HomeViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        val pref = SessionPreference.getInstance(dataStore)
        vm = ViewModelProvider(this, ViewModelFactory(pref))[HomeViewModel::class.java]
        /*vm.getUserName().observe(this) { name ->
            if (!name.isNullOrEmpty()) {
                binding.tvTitleWelcome.text = "Hello, $name!"
            }
        }*/

        var totalPoints = intent.getIntExtra(POIN, 0)

        vm.totalPoints.observe(this) { totalpts ->
            binding.tvValueTotalPt.text = totalpts.toString()
        }

        addItemAndUpdatePoints(totalPoints)

        if (totalPoints != null) {
            binding.apply {
                tvValueTotalPt.text = totalPoints.toString()
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

    private fun addItemAndUpdatePoints(itemPoin: Int) {
        vm.updatePoinAfterAddItem(itemPoin)
    }

    companion object {
        const val POIN = "poin"
    }
}