package com.manpro.recobapp.ui.welcome.auth.account

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.manpro.recobapp.R
import com.manpro.recobapp.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment : Fragment() {

    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResetPasswordBinding.inflate(
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
                parentFragmentManager.beginTransaction().remove(this@ResetPasswordFragment).commitAllowingStateLoss()
            }

            btnSend.setOnClickListener {
                replaceFragment(GreetingFragment())
                /**
                 *
                 * UPCOMIING
                 *
                 * */
            }

            editNewPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!isValidPassword(s)) {
                        editNewPassword.error = "Password minimal 8 karakter"
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    isValidButton()
                }
            })

            editNewPasswordConfirmation.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!isValidPassword(s)) {
                        editNewPassword.error = "Password minimal 8 karakter"
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
        val newFragment = GreetingFragment()

        fragmentTransaction?.replace(R.id.layoutReset, newFragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }

    private fun isValidPassword(password: CharSequence?): Boolean {
        return password!!.length >= 8 && !password.contains(" ")
    }

    private fun isValidButton() {
        val newPassword = binding.editNewPassword.text.toString()
        val confirmPassword = binding.editNewPassword.text.toString()

        binding.btnSend.isEnabled = newPassword == confirmPassword && isValidPassword(newPassword)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}