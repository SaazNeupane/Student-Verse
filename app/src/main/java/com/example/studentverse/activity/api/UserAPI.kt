package com.example.studentverse.activity.api

import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.response.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UserAPI {

    //Register User
    @POST("signup")
    suspend fun userRegister(
        @Body user: User
    ): Response<RegisterLoginResponse>

    //Login User
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

    //Single User
    @GET("user/{id}")
    suspend fun finduser(
        @Path("id") uid: String,
    ):Response<CurrentUserResponse>

    //Update Client Details
    @PUT("profile/update")
    suspend fun update(
        @Header("Authorization") token: String,
        @Body user: User
    ): Response<UpdateResponse>

    //Search User
    @GET("searchUser")
    suspend fun searchtag(
        @Query("name") text:String
    ):Response<SearchUserResponse>

    //Image Change
    @Multipart
    @PUT("/picture/update")
    suspend fun uploadImage(
        @Header("Authorization") token: String,
        @Part picture: MultipartBody.Part
    ): Response<ImageResponse>

    //Update Password
    @FormUrlEncoded
    @PUT("profile/updatePassword")
    suspend fun updatepassword(
        @Header("Authorization") token : String,
        @Field("password") password: String,
        @Field("newPassword") newPassword: String
    ):Response<UpdateResponse>

}