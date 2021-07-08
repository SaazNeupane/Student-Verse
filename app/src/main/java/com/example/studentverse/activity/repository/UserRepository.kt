package com.example.studentverse.activity.repository

import com.example.studentverse.activity.api.APIRequest
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.api.UserAPI
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.response.RegisterLoginResponse

class UserRepository:APIRequest() {
    private val clientAPI = ServiceBuilder.buildService(UserAPI::class.java)

    //register User
    suspend fun userRegister(user: User): RegisterLoginResponse {
        return apiRequest {
            clientAPI.userRegister(user)
        }
    }
}