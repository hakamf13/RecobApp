package com.manpro.recobapp.ui.welcome.auth.login

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.klinker.android.link_builder.Link
import com.klinker.android.link_builder.applyLinks
import com.manpro.recobapp.R
import com.manpro.recobapp.databinding.ActivityLoginBinding
import com.manpro.recobapp.ui.bottomnav.home.HomeActivity
import com.manpro.recobapp.ui.welcome.auth.account.ForgotPasswordFragment
import com.manpro.recobapp.ui.welcome.auth.account.VerifyAccountFragment
import com.manpro.recobapp.ui.welcome.auth.register.RegisterActivity
import com.manpro.recobapp.ui.welcome.splash.dataStore
import com.manpro.recobapp.utils.LoadingBar
import com.manpro.recobapp.utils.SessionPreference

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private lateinit var vm: LoginViewModel
    private lateinit var loading: LoadingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListener()
        setCustomLink()

        val pref = SessionPreference.getInstance(dataStore)
        vm = ViewModelProvider(this, ViewModelFactory(pref))[LoginViewModel::class.java]

        loading = LoadingBar(this)
        vm.isLoading.observe(this) {
            showLoading(it)
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
                    this@LoginActivity, HomeActivity::class.java
                ))
            }
        }

    }

    private fun setCustomLink() {
        forgotPasswordLink()
        registerAccountLink()
    }

    private fun forgotPasswordLink() {
        val forgotLink = Link("Lupa Password?")
            .setTextColor(Color.parseColor("#049E87"))
            .setHighlightAlpha(.4f)
            .setUnderlined(false)
            .setBold(false)
            .setOnClickListener {
                replaceFragment(ForgotPasswordFragment())
            }
        val bindingLink = binding.tvForgotPasswordLink
        bindingLink.applyLinks(forgotLink)
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
        fragmentTransaction.replace(
            R.id.layout,
            fragment,
            "ForgotPassword"
        )
        fragmentManager.popBackStack("ForgotPassword", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        fragmentTransaction.addToBackStack("ForgotPassword")
        fragmentTransaction.commit()

    }

    private fun registerAccountLink() {
        val registerLink = Link("Daftar di sini")
            .setTextColor(Color.parseColor("#049E87"))
            .setHighlightAlpha(.4f)
            .setUnderlined(false)
            .setBold(false)
            .setOnClickListener {
                startActivity(Intent(
                    this@LoginActivity,
                    RegisterActivity::class.java
                ))
            }
        val bindingLink = binding.registerLink
        bindingLink.applyLinks(registerLink)
    }

    private fun setListener() {
        binding.apply {

            binding.editEmail.addTextChangedListener(object : TextWatcher {

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

            binding.editPassword.addTextChangedListener(object : TextWatcher {

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

            binding.btnLogin.setOnClickListener {
                vm.login(
                    binding.editEmail.text.toString(),
                    binding.editPassword.text.toString()
                )
            }

            /*binding.btnLogin.setOnClickListener {
                startActivity(Intent(
                    this@LoginActivity,
                    HomeActivity::class.java
                ))
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
            !TextUtils.isEmpty(binding.editPassword.text.toString())) {
            binding.btnLogin.isEnabled = true
        } else {
            binding.btnLogin.isEnabled = false
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            loading.startLoading()
        } else {
            loading.isDismiss()
        }
    }


}