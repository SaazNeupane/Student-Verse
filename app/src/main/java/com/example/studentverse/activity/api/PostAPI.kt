package com.example.studentverse.activity.api

import com.example.studentverse.activity.model.Answer
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.response.AddPostResponse
import com.example.studentverse.activity.response.AnswerAddResponse
import com.example.studentverse.activity.response.AnswerResponse
import com.example.studentverse.activity.response.PostResponse
import retrofit2.Response
import retrofit2.http.*

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

    //Add Answer
    @POST("addAnswer")
    suspend fun answeradd(
        @Header("Authorization") token : String,
        @Body answer: Answer
    ): Response<AnswerAddResponse>

    //Get Answer
    @GET("answers/{id}")
    suspend fun getanswer(
        @Path("id") id: String
    ):Response<AnswerResponse>
}