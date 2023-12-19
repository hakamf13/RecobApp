package com.manpro.recobapp.ui.menu.recycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.manpro.recobapp.R
import com.manpro.recobapp.data.local.model.recycle.RecycleModel
import com.manpro.recobapp.ui.menu.recycle.checkout.CartModel
import com.manpro.recobapp.utils.Event
import com.manpro.recobapp.utils.SessionPreference

class RecycleViewModel(): ViewModel() {

    private val _item = MutableLiveData<List<RecycleModel>>()
    val goods: MutableLiveData<List<RecycleModel>> = _item

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    /*fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }*/

    /*fun getItem(): List<RecycleModel> {
        val list = listOf(
            RecycleModel(
                name = "TV Tabung",
                value = "20000",
                photoUrl = R.drawable.data1
            ),
            RecycleModel(
                name = "Selang",
                value = "1000",
                photoUrl = R.drawable.data2
            ),
            RecycleModel(
                name = "Pecahan Kaca",
                value = "100",
                photoUrl = R.drawable.data3
            ),
            RecycleModel(
                name = "Plastik Warna",
                value = "200",
                photoUrl = R.drawable.data4
            )
        )

        _item.value = list
        _status.value = true
        return list
    }*/



}