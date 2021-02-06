package com.tatvasoft.apurv.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatvasoft.apurv.ApiHelper
import com.tatvasoft.apurv.data.model.ApiUser
import com.tatvasoft.apurv.utils.Resource
import com.tatvasoft.apurv.utils.Resource.Companion.error
import com.tatvasoft.apurv.utils.Resource.Companion.loading
import com.tatvasoft.apurv.utils.Resource.Companion.success
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val apiHelper: ApiHelper,
) : ViewModel() {

    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                val usersFromApi = apiHelper.getUsers()
                users.postValue(Resource.success(usersFromApi))
            } catch (e: Exception) {
                users.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }

}