package com.example.studentverse.activity.repository

import com.example.studentverse.activity.api.APIRequest
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.api.UserAPI
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.response.*
import okhttp3.MultipartBody

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
    suspend fun profile(): CurrentUserResponse {
        return apiRequest {
            userAPI.profile(ServiceBuilder.token!!)
        }
    }

    //find user
    suspend fun finduser(uid:String): CurrentUserResponse {
        return apiRequest {
            userAPI.finduser(uid)
        }
    }

    //Update
    suspend fun update(user:User): UpdateResponse {
        return apiRequest {
            userAPI.update(
                ServiceBuilder.token!!,user
            )
        }
    }

    //Seacrh User
    suspend fun searchuser(text:String): SearchUserResponse {
        return apiRequest {
            userAPI.searchtag(text)
        }
    }
    //Upload Photo
    suspend fun uploadImage(body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            userAPI.uploadImage(ServiceBuilder.token!!, body)
        }
    }
    //Update Password
    suspend fun changepassword(password:String, newPassword:String):UpdateResponse{
        return apiRequest {
            userAPI.updatepassword(ServiceBuilder.token!!,password,newPassword)
        }
    }



}