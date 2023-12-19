package com.manpro.recobapp.ui.welcome.onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.manpro.recobapp.R
import com.manpro.recobapp.databinding.ActivityOnboardBinding
import com.manpro.recobapp.ui.ViewPagerAdapter
import com.manpro.recobapp.ui.welcome.auth.login.LoginActivity
import com.manpro.recobapp.ui.welcome.onboard.fragments.FirstBoardFragment
import com.manpro.recobapp.ui.welcome.onboard.fragments.SecondBoardFragment
import com.manpro.recobapp.ui.welcome.onboard.fragments.ThirdBoardFragment

class OnboardActivity : AppCompatActivity() {

    private val binding: ActivityOnboardBinding by lazy {
        ActivityOnboardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragmentList = arrayListOf<Fragment>(
            FirstBoardFragment(),
            SecondBoardFragment(),
            ThirdBoardFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            this.supportFragmentManager,
            lifecycle
        )

        binding.vpOnboard.adapter = adapter
        binding.dotsIndicator.attachTo(viewPager2 = binding.vpOnboard)

        binding.btnStart.setOnClickListener {
            finish()
            startActivity(Intent(
                this@OnboardActivity,
                LoginActivity::class.java
            ))
        }

    }
}