package com.manpro.recobapp.ui.bottomnav.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manpro.recobapp.utils.SessionPreference

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val pref: SessionPreference): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

}