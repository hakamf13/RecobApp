package com.manpro.recobapp.ui.welcome.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.manpro.recobapp.R
import com.manpro.recobapp.databinding.ActivityRegisterBinding
import com.manpro.recobapp.ui.welcome.auth.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backNav.setOnClickListener{
            startActivity(Intent(
                this@RegisterActivity,
                LoginActivity::class.java
            ))
        }
    }
}