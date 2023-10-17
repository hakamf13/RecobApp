package com.manpro.recobapp.ui.bottomnav.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manpro.recobapp.databinding.ActivityHomeBinding
import com.manpro.recobapp.ui.welcome.auth.login.LoginActivity
import com.manpro.recobapp.ui.welcome.auth.register.RegisterActivity

class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        /*binding..setOnClickListener{
            startActivity(Intent(
                this@HomeActivity,
                LoginActivity::class.java
            ))
        }*/

        binding.checkLogin.setOnClickListener {
            startActivity(Intent(
                this@HomeActivity,
                LoginActivity::class.java
                ))
        }

        binding.checkRegister.setOnClickListener {
            startActivity(Intent(
                this@HomeActivity,
                RegisterActivity::class.java
            ))
        }

    }
}