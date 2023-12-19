package com.manpro.recobapp.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
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

        fun getInstance(dataStore: DataStore<Preferences>): SessionPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = SessionPreference(dataStore)
                INSTANCE = instance
                instance
            }
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

    fun getCart(): Flow<Unit> {
        return dataStore.data.map { preferences ->
            val cartListJson = preferences[CART] ?: null
            val  cartList = Gson().fromJson(cartListJson, Array<CartModel>::class.java).toList()
            cartList
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