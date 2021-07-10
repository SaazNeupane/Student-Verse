package com.example.studentverse.activity.api

import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.response.RegisterLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserAPI {

    //Register User
    @POST("signup")
    suspend fun userRegister(
        @Body user: User
    ): Response<RegisterLoginResponse>

    //Login Client
    @FormUrlEncoded
    @POST("login")
    suspend fun checkclient(
        @Field("username") username: String,
        @Field("password") password: String
    ):Response<RegisterLoginResponse>
}