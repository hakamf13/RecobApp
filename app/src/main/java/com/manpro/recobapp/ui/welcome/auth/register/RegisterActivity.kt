package com.manpro.recobapp.ui.welcome.auth.register

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import com.klinker.android.link_builder.Link
import com.klinker.android.link_builder.applyLinks
import com.manpro.recobapp.R
import com.manpro.recobapp.databinding.ActivityRegisterBinding
import com.manpro.recobapp.ui.welcome.auth.account.VerifyAccountFragment
import com.manpro.recobapp.ui.welcome.auth.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListener()
        setCustomLink()

        binding.backNav.setOnClickListener{
            startActivity(Intent(
                this@RegisterActivity,
                LoginActivity::class.java
            ))
        }
    }

    private fun setListener() {
        binding.apply {

            backNav.setOnClickListener {
                startActivity(Intent(
                    this@RegisterActivity,
                    LoginActivity::class.java
                ))
            }

            editEmail.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!isValidEmail(s)) {
                        binding.editEmail.error = "Format email salah. (ex: xxx@gmail.com)"
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    isValidButton()
                }

            })

            editName.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    isValidButton()
                }

            })

            editPhone.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    isValidButton()
                }

            })

            editPassword.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!isValidPassword(s)) {
                        editPassword.error = "Password minimal 8 karakter"
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    isValidButton()
                }

            })

            isValidButton()

            btnRegister.setOnClickListener {
                // Di dalam method onClick btnRegister
                val verifyAccountFragment = VerifyAccountFragment() // Buat instance fragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.layout, verifyAccountFragment)
                    .addToBackStack(null) // Jika Anda ingin menambahkan ke back stack
                    .commit()

            }

        }
    }

    private fun isValidEmail(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidPassword(password: CharSequence?): Boolean {
        return password!!.length >= 8 && !password.contains(" ")
    }

    private fun isValidButton() {
        if (binding.editEmail.error == null &&
            binding.editPassword.error == null &&
            !TextUtils.isEmpty(binding.editEmail.text.toString()) &&
            !TextUtils.isEmpty(binding.editPassword.text.toString())) {
            binding.btnRegister.isEnabled = true
        } else {
            binding.btnRegister.isEnabled = false
        }
    }

    private fun setCustomLink() {
        val loginLink = Link("Masuk")
            .setTextColor(Color.parseColor("#049E87"))
            .setHighlightAlpha(.4f)
            .setUnderlined(false)
            .setBold(false)
            .setOnClickListener {
                startActivity(Intent(
                    this@RegisterActivity,
                    LoginActivity::class.java
                ))
            }
        val bindingLink = binding.loginLink
        bindingLink.applyLinks(loginLink)
    }


}