package com.tatvasoft.apurv

import com.tatvasoft.apurv.data.model.ApiUser
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<ApiUser>

}