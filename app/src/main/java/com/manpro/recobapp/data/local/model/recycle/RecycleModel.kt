package com.manpro.recobapp.data.local.model.recycle

import androidx.room.PrimaryKey

data class RecycleModel(

    @PrimaryKey
    val id: Int,

    val name: String,

    val value: Int,

    val photoUrl: String

)