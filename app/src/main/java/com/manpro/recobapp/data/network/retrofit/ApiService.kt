package com.manpro.recobapp.data.network.retrofit

import com.manpro.recobapp.data.network.response.UserForgotResponse
import com.manpro.recobapp.data.network.response.UserLoginResponse
import com.manpro.recobapp.data.network.response.UserRegisterResponse
import com.manpro.recobapp.data.network.response.UserResetResponse
import com.manpro.recobapp.data.network.response.UserVerifyResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    /*@POST("auth/user/signup")
    fun userPostRegister(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("password") password: String
    ): Call<UserRegisterResponse>*/

    /*@POST("auth/user/signin")
    fun userPostLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<UserLoginResponse>*/

    @POST("auth/user/signup")
    fun userPostRegister(
        @Body requestBody: RequestBody
    ): Call<UserRegisterResponse>

    @POST("auth/user/signin")
    fun userPostLogin(
        @Body requestBody: RequestBody
    ) : Call<UserLoginResponse>

    @POST("auth/user/forgotpassword")
    fun userPostForgotPassword(
        @Field("email") email: String
    ) : Call<UserForgotResponse>

    @POST("auth/user/verificationcode")
    fun userPostVerificationCode(
        @Field("email") email: String,
        @Field("code") code: String
    ) : Call<UserVerifyResponse>

    @FormUrlEncoded
    @PUT("auth/user/resetpassword")
    fun userPutResetPassword(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("passwordAgain") passwrodAgain: String,
        @Field("code") code: String
    ) : UserResetResponse

}