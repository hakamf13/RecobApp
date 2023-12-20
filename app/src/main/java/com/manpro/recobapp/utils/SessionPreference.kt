package com.manpro.recobapp.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.manpro.recobapp.data.local.model.recycle.RecycleModel
import com.manpro.recobapp.ui.menu.recycle.checkout.CartModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SessionPreference private constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        @Volatile
        private var INSTANCE: SessionPreference? = null

        private val TOKEN = stringPreferencesKey("token")
        private val NAME = stringPreferencesKey("name")
        private val STATE = stringPreferencesKey("state")
        private val CART = stringPreferencesKey("cart")
        private val USER_NAME_KEY = stringPreferencesKey("user_name")
        private val POIN = intPreferencesKey("poin")


        fun getInstance(dataStore: DataStore<Preferences>): SessionPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = SessionPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun setUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = name
        }
    }

    fun getUserName(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[USER_NAME_KEY]
        }
    }

    fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN] ?: ""
        }
    }

    suspend fun setToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN] ?: token
        }
    }

    suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    fun getPoin(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[POIN] ?: 0
        }
    }

    suspend fun setPoin(poin: Int) {
        dataStore.edit { preferences ->
            preferences[POIN] = poin
        }
    }

    fun getCart(): Flow<List<RecycleModel>> {
        return dataStore.data.map { preferences ->
            val itemListJson = preferences[CART] ?: "[]"
            Gson().fromJson(itemListJson, Array<RecycleModel>::class.java).toList()
        }
    }

    suspend fun saveCart(itemList: List<RecycleModel>) {
        val itemListJson = Gson().toJson(itemList)
        dataStore.edit { preferences ->
            preferences[CART] = itemListJson
        }
    }

    suspend fun setTotalPoints(poin: Int) {
        dataStore.edit { preferences ->
            preferences[POIN] = poin
        }
    }

    fun getTotalPoints(): Flow<Int> {
        return dataStore.data.map { preferences ->
            preferences[POIN] ?: 0
        }
    }

/*    fun getCartCounter()

    suspend fun saveCart(cartList: CartModel) {
        val cartListJson = Gson().toJson(cartList)
        dataStore.edit { preferences ->
            preferences[CART] = cartListJson
        }
    }*/

}