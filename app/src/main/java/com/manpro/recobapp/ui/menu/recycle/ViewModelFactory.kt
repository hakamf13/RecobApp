package com.manpro.recobapp.ui.menu.recycle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manpro.recobapp.utils.SessionPreference

class ViewModelFactory(private val pref: SessionPreference): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(RecycleViewModel::class.java)) {
            return RecycleViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}