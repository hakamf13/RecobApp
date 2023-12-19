package com.manpro.recobapp.data.network.response

import com.google.gson.annotations.SerializedName

data class UserLoginResponse(

	@field:SerializedName("statusCode")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: DataLogin,

	@field:SerializedName("error")
	val error: Boolean

)

data class DataLogin(

	@field:SerializedName("access_token")
	val access_token: String

)
