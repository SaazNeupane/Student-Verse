package com.example.studentverse.activity.repository

import com.example.studentverse.activity.api.APIRequest
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.api.UserAPI
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.response.CurrentUserRespone
import com.example.studentverse.activity.response.RegisterLoginResponse

class UserRepository:APIRequest() {
    private val userAPI = ServiceBuilder.buildService(UserAPI::class.java)

    //register User
    suspend fun userRegister(user: User): RegisterLoginResponse {
        return apiRequest {
            userAPI.userRegister(user)
        }
    }

    //login Client
    suspend fun checkuser(username:String, password:String):RegisterLoginResponse{
        return apiRequest {
            userAPI.checkclient(username,password)
        }
    }

    //current user
    suspend fun profile(): CurrentUserRespone {
        return apiRequest {
            userAPI.profile(ServiceBuilder.token!!)
        }
    }

}