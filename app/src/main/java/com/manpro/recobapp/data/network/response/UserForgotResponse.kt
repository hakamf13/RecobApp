package com.manpro.recobapp.data.network.response

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class UserForgotResponse(

	@field:SerializedName("statusCode")
	val statusCode: Int,

	@field:SerializedName("data")
	val data: DataForgot,

	@field:SerializedName("error")
	val error: Boolean

)

data class DataForgot(

	@field:SerializedName("otp_id")
	val otp_id: Int,

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("expirationDate")
	val expirationDate: String,

	@field:SerializedName("createdAt")
	val createdAt: String

)
