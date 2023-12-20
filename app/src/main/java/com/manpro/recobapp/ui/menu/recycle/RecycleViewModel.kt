package com.manpro.recobapp.ui.menu.recycle

import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.manpro.recobapp.R
import com.manpro.recobapp.data.local.model.recycle.RecycleModel
import com.manpro.recobapp.utils.Event
import com.manpro.recobapp.utils.SessionPreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecycleViewModel(private val sessionPreference: SessionPreference): ViewModel() {

    private val _poin = MutableLiveData<Int>()
    val poin: LiveData<Int> = _poin

    private val _listItem = MutableLiveData<List<RecycleModel>>()
    val listItem: LiveData<List<RecycleModel>> = _listItem

    init {
        observePoin()
        observeItemList()
    }

    private fun observePoin() {
        viewModelScope.launch {
            sessionPreference.getPoin().collect {
                _poin.value = it
            }
        }
    }

    private fun observeItemList() {
        viewModelScope.launch {
            sessionPreference.getCart().collect {
                _listItem.value = it
            }
        }
    }

    fun updatePoinAfterAddItem(itemPoin: Int) {
        viewModelScope.launch {
            val currentPoin = _poin.value ?: 0
            sessionPreference.setPoin(currentPoin + itemPoin)
        }
    }

    fun addItemToCart(item: RecycleModel) {
        viewModelScope.launch {
            val currentListItem = _listItem.value ?: emptyList()
            val updateListItem = currentListItem.toMutableList()
            updateListItem.add(item)
            sessionPreference.saveCart(updateListItem)
        }
    }

    private val poinKey = stringPreferencesKey("poin")

    /*val totalPoints: Flow<Int> = getDataStore.data.map { preferences ->
        preferences[poinKey] ?: 0
    }*/

}
