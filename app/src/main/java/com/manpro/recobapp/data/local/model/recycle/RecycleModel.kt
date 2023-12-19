package com.manpro.recobapp.data.local.model.recycle

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecycleModel(

    val name: String,

    val value: String,

    val photoUrl: Int

) : Parcelable