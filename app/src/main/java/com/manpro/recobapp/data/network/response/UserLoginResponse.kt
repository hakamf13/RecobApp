package com.manpro.recobapp.data.network.response

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("email")
	val email: String
)
