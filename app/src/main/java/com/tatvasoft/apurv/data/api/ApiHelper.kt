package com.tatvasoft.apurv

import com.tatvasoft.apurv.data.model.ApiUser

interface ApiHelper {

    suspend fun getUsers(offset: Int, limit: Int): ApiUser

}