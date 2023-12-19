package com.manpro.recobapp.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.manpro.recobapp.R

class GenericTextWatcher internal constructor(
    private val currentView: View,
    private val nextView: View?
): TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun afterTextChanged(s: Editable?) {
        val text = s.toString()
        when (currentView.id) {
            R.id.edit_otp1 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.edit_otp2 -> if (text.length == 1) nextView!!.requestFocus()
            R.id.edit_otp3 -> if (text.length == 1) nextView!!.requestFocus()
        }
    }

}