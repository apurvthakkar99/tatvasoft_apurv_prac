package com.tatvasoft.apurv.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tatvasoft.apurv.ApiHelper
import com.tatvasoft.apurv.data.viewmodel.MainActivityViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(apiHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}