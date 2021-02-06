package com.tatvasoft.apurv

import com.tatvasoft.apurv.data.model.ApiUser

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(offset: Int, limit: Int): List<ApiUser> = apiService.getUsers(offset,limit)

}