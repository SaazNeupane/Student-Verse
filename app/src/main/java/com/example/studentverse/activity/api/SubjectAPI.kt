package com.example.studentverse.activity.api

import com.example.studentverse.activity.response.*
import retrofit2.Response
import retrofit2.http.*

interface SubjectAPI {

    //subject
    @GET("subject")
    suspend fun subject(
        @Header("Authorization") token : String,
    ): Response<SubjectResponse>

    //Get Topic
    @GET("topic")
    suspend fun gettopic(
        @Query("subjectID") studentID:String
    ):Response<TopicResponse>

    //Get Chapter
    @GET("chapter/{subject}/{topic}")
    suspend fun getchapter(
        @Path("subject") sid: String,
        @Path("topic") tid: String,
    ):Response<ChapterResponse>

    //Get Quiz
    @GET("quiz/{chapterid}")
    suspend fun getquiz(
        @Path("chapterid") id: String,
    ):Response<QuizResponse>

    //Add Score
    @FormUrlEncoded
    @POST("score")
    suspend fun checkclient(
        @Header("Authorization") token : String,
        @Field("score") score: String,
        @Field("quizname") quizname: String,
        @Field("time") time: String
    ):Response<ScoreResponse>
}