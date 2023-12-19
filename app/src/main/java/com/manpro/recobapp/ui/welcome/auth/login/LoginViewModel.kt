package com.manpro.recobapp.ui.welcome.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.manpro.recobapp.data.network.response.UserLoginResponse
import com.manpro.recobapp.data.network.retrofit.ApiConfig
import com.manpro.recobapp.utils.Event
import com.manpro.recobapp.utils.SessionPreference
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginViewModel(private val pref: SessionPreference) : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun setToken(token: String) {
        viewModelScope.launch {
            pref.setToken(token)
        }
    }

    fun login(email: String, password: String) {
        _isLoading.value = true
        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("password", password)
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val client = ApiConfig.getApiService().userPostLogin(requestBody)
        client.enqueue(object : Callback<UserLoginResponse> {

            override fun onResponse(
                call: Call<UserLoginResponse>,
                response: Response<UserLoginResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    try {
                        val token = response.body()!!.data.access_token
                        setToken(token)
                        _status.value = true
                        return
                    } catch (_: Exception) {

                    }
                }
                _isLoading.value = false
                _snackbarText.value = Event(response.message())
            }

            override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = Event(t.message.toString())
            }

        })
    }

}