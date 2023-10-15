package com.manpro.recobapp.data.network.response

import com.google.gson.annotations.SerializedName

data class UserResetResponse(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("passwordAgain")
	val passwordAgain: String,

	@field:SerializedName("email")
	val email: String
)
