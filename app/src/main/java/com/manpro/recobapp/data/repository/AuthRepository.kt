package com.manpro.recobapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.manpro.recobapp.data.network.response.UserRegisterResponse
import com.manpro.recobapp.data.network.retrofit.ApiService
import com.manpro.recobapp.utils.Result.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(
    private val apiService: ApiService
) {

    companion object {
        @Volatile
        private var INSTANCE : AuthRepository? = null

        fun getAuthRepositoryInstance(
            apiService: ApiService
        ) : AuthRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: AuthRepository(
                    apiService
                )
            }.also {
                INSTANCE = it
            }
    }

    private val registerResponse = MediatorLiveData<Result<UserRegisterResponse>>()

    fun userPostRegister(
        email: String,
        name: String,
        password: String,
        passwordAgain: String
    ) : LiveData<Result<UserRegisterResponse>> {
//        registerResponse.value = com.manpro.recobapp.utils.Result
        val client = apiService.userPostRegister(
            email, name, password, passwordAgain
        )
        client.enqueue(object : Callback<UserRegisterResponse> {
            override fun onResponse(
                call: Call<UserRegisterResponse>,
                response: Response<UserRegisterResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()!!
                    if (responseBody.statusCode == 200) {

                    }
                }
            }

            override fun onFailure(call: Call<UserRegisterResponse>, t: Throwable) {

            }
        })

        return registerResponse
    }

}