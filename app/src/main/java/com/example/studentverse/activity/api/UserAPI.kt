package com.example.studentverse.activity.api

import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.response.CurrentUserResponse
import com.example.studentverse.activity.response.PostResponse
import com.example.studentverse.activity.response.RegisterLoginResponse
import retrofit2.Response
import retrofit2.http.*

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

    //current user
    @GET("profile")
    suspend fun profile(
        @Header("Authorization") token : String,
    ):Response<CurrentUserResponse>




}