package com.manpro.recobapp.ui.welcome.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.manpro.recobapp.data.network.response.UserRegisterResponse
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

class RegisterViewModel(private val pref: SessionPreference) : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    fun saveUserData(name: String) {
        viewModelScope.launch {
            pref.setUserName(name)
        }
    }

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun setToken(token: String) {
        viewModelScope.launch {
            pref.setToken(token)
        }
    }

    fun register(
        email: String,
        name: String,
        phone: String,
        password: String,
        passwordAgain: String,
        function: () -> Unit
    ) {
        _isLoading.value = true
        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("name", name)
        jsonObject.put("alamat", phone)
        jsonObject.put("password", password)
        jsonObject.put("passwordAgain", passwordAgain)
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        val client = ApiConfig.getApiService().userPostRegister(requestBody)
        client.enqueue(object : Callback<UserRegisterResponse> {

            override fun onResponse(
                call: Call<UserRegisterResponse>,
                response: Response<UserRegisterResponse>
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

            override fun onFailure(call: Call<UserRegisterResponse>, t: Throwable) {
                _isLoading.value = false
                _snackbarText.value = Event(t.message.toString())
            }

        })
    }

}