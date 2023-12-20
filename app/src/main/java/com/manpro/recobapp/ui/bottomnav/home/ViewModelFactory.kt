package com.manpro.recobapp.ui.bottomnav.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manpro.recobapp.utils.SessionPreference

class ViewModelFactory(private val pref: SessionPreference): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}