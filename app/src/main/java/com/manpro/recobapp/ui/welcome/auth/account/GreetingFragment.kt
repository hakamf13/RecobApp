package com.manpro.recobapp.ui.welcome.auth.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.manpro.recobapp.databinding.FragmentGreetingBinding
import com.manpro.recobapp.ui.bottomnav.home.HomeActivity

class GreetingFragment : Fragment() {

    private var _binding: FragmentGreetingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGreetingBinding.inflate(
            inflater,
            container,
            false
        )
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
    }

    private fun setListener() {
        binding.apply {
            btnStart.setOnClickListener {
                requireActivity().finish()
                startActivity(Intent(
                    requireActivity(),
                    HomeActivity::class.java
                ))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}