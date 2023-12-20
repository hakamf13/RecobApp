package com.manpro.recobapp.ui.menu.recycle.checkout

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartModel(
    var photoUrl: Int,
    var nama: String = "",
    var quantity: Int = 0,
    var value: Int = 0,
    var point: Int = 0,
    var totalPoint: Int = 0
) : Parcelable