package com.tatvasoft.apurv.data.model

import com.google.gson.annotations.SerializedName


data class ApiUser(
    val `data`: Data,
    val message: Any,
    val status: Boolean
) {
    data class Data(
        val has_more: Boolean,
        val users: List<User>
    ) {
        data class User(
            val image: String,
            val items: List<String>,
            val name: String
        )
    }
}