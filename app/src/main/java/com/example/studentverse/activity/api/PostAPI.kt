package com.example.studentverse.activity.api

import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.response.AddPostResponse
import com.example.studentverse.activity.response.PostResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PostAPI {

    //Add Ad
    @POST("addQuestion")
    suspend fun addAd(
        @Header("Authorization") token : String,
        @Body post : Post
    ): Response<AddPostResponse>

    //get post
    @GET("posts")
    suspend fun post(
        @Header("Authorization") token : String,
    ):Response<PostResponse>
}