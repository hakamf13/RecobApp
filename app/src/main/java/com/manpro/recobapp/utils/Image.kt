package com.manpro.recobapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(avatarUrl: Int?) {
    Glide.with(this.context) .load(avatarUrl) .apply(
        RequestOptions().override(200, 200)
    ) .centerCrop() .into(this)
}