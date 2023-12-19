package com.manpro.recobapp.utils

import android.app.Activity
import android.app.Dialog
import android.view.Window
import com.manpro.recobapp.R

@Suppress("DEPRECATION")
class LoadingBar(val mActivity: Activity) {

    private lateinit var isDialog: Dialog

    fun startLoading() {
        val inflater = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.custom_loading_bar, null)

        isDialog = Dialog(mActivity)
        isDialog.window?.decorView?.rootView?.setBackgroundResource(R.color.transparent)
        isDialog.window?.decorView?.setOnApplyWindowInsetsListener { _, insets ->
            insets.consumeSystemWindowInsets()
        }

        isDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        isDialog.setContentView(dialogView)
        isDialog.setCancelable(false)
        isDialog.show()
    }

    fun isDismiss() {
        isDialog.dismiss()
    }

}