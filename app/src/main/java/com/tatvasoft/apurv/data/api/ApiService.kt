package com.tatvasoft.apurv

import com.tatvasoft.apurv.data.model.ApiUser
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(
        @Query("offset") pageSize: Int,
        @Query("limit") currentPage: Int
    ): List<ApiUser>
}