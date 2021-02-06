package com.tatvasoft.apurv

import com.tatvasoft.apurv.data.model.ApiUser

interface ApiHelper {

    suspend fun getUsers(): List<ApiUser>

}