package com.tatvasoft.apurv.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tatvasoft.apurv.ApiHelper
import com.tatvasoft.apurv.data.model.ApiUser
import com.tatvasoft.apurv.utils.Resource
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val apiHelper: ApiHelper,
) : ViewModel() {

    private val users = MutableLiveData<Resource<ApiUser>>()
    private var INITIAL_PAGE_OFFSET = 0
    private var PAGE_LIMIT = 10

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                val usersFromApi = apiHelper.getUsers(INITIAL_PAGE_OFFSET,PAGE_LIMIT)
                users.postValue(Resource.success(usersFromApi))
            } catch (e: Exception) {
                users.postValue(Resource.error(e.toString(), null))
            }
        }
    }

    fun getUsers(): LiveData<Resource<ApiUser>> {
        return users
    }

}