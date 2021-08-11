package com.example.studentverse.activity.api

import com.example.studentverse.activity.response.ChapterResponse
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

    //Get Answer
    @GET("topic")
    suspend fun gettopic(
        @Query("subjectID") studentID:String
    ):Response<TopicResponse>

    //Get Answer
    @GET("chapter")
    suspend fun getchapter(
        @Body subject: String
    ):Response<ChapterResponse>
}