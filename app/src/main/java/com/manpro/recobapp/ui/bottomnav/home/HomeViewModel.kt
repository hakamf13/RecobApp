package com.manpro.recobapp.ui.bottomnav.home

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.manpro.recobapp.data.network.response.DataLogin
import com.manpro.recobapp.data.network.response.UserLoginResponse
import com.manpro.recobapp.data.network.retrofit.ApiConfig
import com.manpro.recobapp.utils.Event
import com.manpro.recobapp.utils.SessionPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(private val preference: SessionPreference): ViewModel() {

    /*fun getUserName(): LiveData<String?> {
        return preference.getUserName().asLiveData()
    }
*/
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val _totalPoints = MutableLiveData<Int>()
    val totalPoints: LiveData<Int> get() = _totalPoints

    init {
        viewModelScope.launch {
            preference.getUserName().collect {
                _userName.value = it
            }

            preference.getTotalPoints().collect {
                _totalPoints.value = it
            }
        }
    }

    fun setPoin(poin: Int) {
        viewModelScope.launch {
            preference.setTotalPoints(poin)
        }
    }

    // Function to update poin after adding an item
    fun updatePoinAfterAddItem(itemPoin: Int) {
        val currentPoin = _totalPoints.value ?: 0
        setPoin(currentPoin + itemPoin)
    }

    private val poinKey = intPreferencesKey("poin")

    // Function to get the user token
    fun getToken(): LiveData<String> {
        return preference.getToken().asLiveData()
    }

    // Function to set the user token
    fun setToken(token: String) {
        viewModelScope.launch {
            preference.setToken(token)
        }
    }

    fun getPoin(): LiveData<Int> {
        return preference.getPoin().asLiveData()
    }

    /*fun setPoin(poin: Int) {
        viewModelScope.launch {
            preference.setPoin(poin)
        }
    }*/

    /*fun updatePoinAfterAddItem(itemPoin: Int) {
        viewModelScope.launch {
            val currentPoin = preference.getPoin().first()
            setPoin(currentPoin + itemPoin)
        }
    }*/

}