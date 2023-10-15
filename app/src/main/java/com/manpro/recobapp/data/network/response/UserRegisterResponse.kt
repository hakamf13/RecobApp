package com.manpro.recobapp.data.network.response

import com.google.gson.annotations.SerializedName

data class UserRegisterResponse(

	/*@field:SerializedName("password")
	val password: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("passwordAgain")
	val passwordAgain: String,

	@field:SerializedName("email")
	val email: String*/

	@field:SerializedName("statusCode")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: Data

)

data class Data(

	@field:SerializedName("access_token")
	val access_token: String

)
