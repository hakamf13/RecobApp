package com.manpro.recobapp.data.network.response

import com.google.gson.annotations.SerializedName

data class UserForgotResponse(

	@field:SerializedName("email")
	val email: String
)
