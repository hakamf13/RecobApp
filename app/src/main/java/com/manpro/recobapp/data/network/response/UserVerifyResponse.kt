package com.manpro.recobapp.data.network.response

import com.google.gson.annotations.SerializedName

data class UserVerifyResponse(

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("email")
	val email: String
)
