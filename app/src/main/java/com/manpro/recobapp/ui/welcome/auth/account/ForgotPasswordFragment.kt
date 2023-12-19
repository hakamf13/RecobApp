package com.manpro.recobapp.ui.welcome.auth.account

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.manpro.recobapp.R
import com.manpro.recobapp.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {

    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgotPasswordBinding.inflate(
            inflater,
            container,
            false
        )
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListener()
    }

    private fun clickListener() {
        binding.apply {

            backNav.setOnClickListener {
                parentFragmentManager.beginTransaction().remove(this@ForgotPasswordFragment).commitAllowingStateLoss()
            }

            btnSend.setOnClickListener {
                replaceFragment(VerifyAccountFragment())
                /**
                 *
                 * UPCOMIING
                 *
                 * */
            }

            editEmailPhone.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!isValidEmail(s)) {
                        binding.editEmailPhone.error = "Format email salah. (ex: xxx@gmail.com)"
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    isValidButton()
                }
            })

        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        val newFragment = VerifyAccountForgotFragment()

        fragmentTransaction?.replace(R.id.layoutForgot, newFragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }

    private fun isValidEmail(email: CharSequence?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidButton() {
        binding.btnSend.isEnabled = binding.editEmailPhone.error == null &&
                !TextUtils.isEmpty(binding.editEmailPhone.text.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}