package com.manpro.recobapp.data.network.response

import com.google.gson.annotations.SerializedName

data class UserRegisterResponse(

	@field:SerializedName("statusCode")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("error")
	val error: Boolean

)

data class Data(

	@field:SerializedName("access_token")
	val access_token: String

)
