package com.tatvasoft.apurv

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers() = apiService.getUsers()

}