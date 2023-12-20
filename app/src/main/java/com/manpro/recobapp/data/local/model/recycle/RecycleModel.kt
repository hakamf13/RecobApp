package com.manpro.recobapp.data.local.model.recycle

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecycleModel(
    /*val name: String,
    val value: String,
    val photoUrl: Int,
    val quantity: Int = 0*/
    var photoUrl: Int,
    var nama: String,
    var quantity: String,
    var value: String,
    var point: String,
    var totalPoint: String
) : Parcelable