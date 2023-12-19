package com.manpro.recobapp.ui.welcome.auth.account

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.klinker.android.link_builder.Link
import com.klinker.android.link_builder.applyLinks
import com.manpro.recobapp.R
import com.manpro.recobapp.databinding.FragmentVerifyAccountBinding
import com.manpro.recobapp.ui.welcome.auth.login.LoginActivity
import com.manpro.recobapp.utils.GenericKeyEvent
import com.manpro.recobapp.utils.GenericTextWatcher

class VerifyAccountFragment: Fragment() {

    private var _binding: FragmentVerifyAccountBinding? = null
    private val binding get() = _binding!!

    private var verificationType: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentVerifyAccountBinding.inflate(
            inflater,
            container,
            false
        )

        val intent = requireActivity().intent
        verificationType = intent.getIntExtra("verificationType", 0)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setVerificationType()
        setOtp()
        setListener()
        setCustomLink()
    }

    private fun setVerificationType() {

        if (verificationType == 1) {
            binding.btnSend.setOnClickListener {
                /**
                 *
                 * UPCOMING
                 *
                 *
                 */
                requireActivity().finish()
                startActivity(Intent(
                    requireContext(),
                    GreetingFragment::class.java
                ))
            }
        } else if (verificationType == 2) {
            /**
             *
             * UPCOMING
             *
             *
             */
            binding.btnSend.setOnClickListener {
                requireActivity().finish()
                startActivity(Intent(
                    requireActivity(),
                    LoginActivity::class.java
                ))
            }
        }


    }

    private fun setCustomLink() {
        val resendLink = Link("Kirim Ulang")
            .setTextColor(Color.parseColor("#049E87"))
            .setHighlightAlpha(.4f)
            .setUnderlined(false)
            .setBold(false)
            .setOnClickListener {
                val intent = Intent(
                    requireContext(),
                    VerifyAccountFragment::class.java
                )
                startActivity(intent)
                /**
                 *
                 * UPCOMING
                 *
                 * **/
            }
        val bindingLink = binding.resendLink
        bindingLink.applyLinks(resendLink)
    }

    private fun setListener() {

        binding.apply {

            backNav.setOnClickListener {
                parentFragmentManager.beginTransaction().remove(this@VerifyAccountFragment).commitAllowingStateLoss()
            }

            btnSend.setOnClickListener {
                requireActivity().finish()
                startActivity(Intent(
                    requireActivity(),
                    LoginActivity::class.java
                ))
            }

        }

    }

    private fun setOtp() {
        binding.apply {
            // GenericTextWatcher here works only for moving to next EditText when a number is entered
            // first parameter is the current EditText and second parameter is next EditText
            editOtp1.addTextChangedListener(GenericTextWatcher(editOtp1, editOtp2))
            editOtp2.addTextChangedListener(GenericTextWatcher(editOtp2, editOtp3))
            editOtp3.addTextChangedListener(GenericTextWatcher(editOtp3, editOtp4))
            editOtp4.addTextChangedListener(GenericTextWatcher(editOtp4, null))

            // GenericKeyEvent here works for deleting the element and to switch back to previous EditText
            // first parameter is the current EditText and second parameter is previous EditText
            editOtp1.setOnKeyListener(GenericKeyEvent(editOtp1, null))
            editOtp2.setOnKeyListener(GenericKeyEvent(editOtp2, editOtp1))
            editOtp3.setOnKeyListener(GenericKeyEvent(editOtp3, editOtp2))
            editOtp4.setOnKeyListener(GenericKeyEvent(editOtp4, editOtp3))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}