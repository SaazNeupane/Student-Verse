package com.example.studentverse.activity.api

import com.example.studentverse.activity.response.AnswerResponse
import com.example.studentverse.activity.response.SubjectResponse
import com.example.studentverse.activity.response.TopicResponse
import retrofit2.Response
import retrofit2.http.*

interface SubjectAPI {

    //subject
    @GET("subject")
    suspend fun subject(
        @Header("Authorization") token : String,
    ): Response<SubjectResponse>

    //subject image
    @GET("subject/{pictureName}")
    suspend fun subjectimage(
        @Header("Authorization") token : String,
        @Path("pictureName") pictureName: String,
        ): Response<SubjectResponse>

    //Get Answer
    @GET("topic")
    suspend fun gettopic(
        @Query("subjectID") studentID:String
    ):Response<TopicResponse>
}