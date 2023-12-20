package com.manpro.recobapp.ui.menu.recycle.checkout

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationModel(
    val name: String,
    val address: String
) : Parcelable