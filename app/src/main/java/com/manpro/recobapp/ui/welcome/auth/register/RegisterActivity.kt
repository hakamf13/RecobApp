package com.manpro.recobapp.ui.welcome.auth.register

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.klinker.android.link_builder.Link
import com.klinker.android.link_builder.applyLinks
import com.manpro.recobapp.databinding.ActivityRegisterBinding
import com.manpro.recobapp.ui.welcome.auth.login.LoginActivity
import com.manpro.recobapp.ui.welcome.splash.dataStore
import com.manpro.recobapp.utils.LoadingBar
import com.manpro.recobapp.utils.SessionPreference

class RegisterActivity : AppCompatActivity() {

    private val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    private lateinit var vm: RegisterViewModel
    private lateinit var loading: LoadingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListener()
        setCustomLink()

        val pref = SessionPreference.getInstance(dataStore)
        vm = ViewModelProvider(this, ViewModelFactory(pref))[RegisterViewModel::class.java]

        loading = LoadingBar(this)
        vm.isLoading.observe(this) {
            showLoading(it)
        }

        binding.backNav.setOnClickListener{
            startActivity(Intent(
                this@RegisterActivity,
                LoginActivity::class.java
            ))
        }

        vm.snackbarText.observe(this) {
            it.getContentIfNotHandled()?.let { it1 ->
                Snackbar.make(
                    window.decorView.rootView,
                    it1,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        vm.status.observe(this) {
            if (it) {
                finish()
                startActivity(Intent(
                    this@RegisterActivity,
                    LoginActivity::class.java
                ))
            }
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

            binding.btnRegister.setOnClickListener {
                vm.register(
                    binding.editEmail.text.toString(),
                    binding.editName.text.toString(),
                    binding.editPhone.text.toString(),
                    binding.editPassword.text.toString(),
                    binding.editPasswordAgain.text.toString()
                    )
            }

            /*btnRegister.setOnClickListener {
                // Di dalam method onClick btnRegister
                val verifyAccountFragment = VerifyAccountFragment() // Buat instance fragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.layout, verifyAccountFragment)
                    .addToBackStack(null) // Jika Anda ingin menambahkan ke back stack
                    .commit()

            }*/

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
            !TextUtils.isEmpty(binding.editPassword.text.toString()) &&
            !TextUtils.isEmpty(binding.editPasswordAgain.text.toString())) {
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

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            loading.startLoading()
        } else {
            loading.isDismiss()
        }
    }

}