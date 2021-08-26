package com.example.studentverse.activity.api

import com.example.studentverse.activity.model.Answer
import com.example.studentverse.activity.model.Comment
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.model.Vote
import com.example.studentverse.activity.response.*
import retrofit2.Response
import retrofit2.http.*

interface PostAPI {

    //Add Question
    @POST("addQuestion")
    suspend fun addAd(
        @Header("Authorization") token : String,
        @Body post : Post
    ): Response<AddPostResponse>

    //Add Answer
    @FormUrlEncoded
    @PUT("post/update")
    suspend fun updatepost(
        @Header("Authorization") token : String,
        @Field("id") id: String,
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("tags") tags: List<String>,
    ): Response<PostResponse>

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

    //Add Comment
    @POST("addComment")
    suspend fun addcomment(
        @Header("Authorization") token : String,
        @Body comment: Comment
    ): Response<CommentAddResponse>

    //Voting
    @POST("upvote")
    suspend fun upvote(
        @Header("Authorization") token : String,
        @Body vote: Vote
    ): Response<VoteResponse>

    @POST("downvote")
    suspend fun downvote(
        @Header("Authorization") token : String,
        @Body vote: Vote
    ): Response<VoteResponse>

    @POST("unvote")
    suspend fun unvote(
        @Header("Authorization") token : String,
        @Body vote: Vote
    ): Response<VoteResponse>

    //Search Question
    @GET("searchPost")
    suspend fun searchquestion(
        @Query("question") text:String
    ):Response<PostResponse>

    //Search Tags
    @GET("searchTag")
    suspend fun searchtag(
        @Query("tags") text:String
    ):Response<PostResponse>

    //My Question
    @GET("userPost")
    suspend fun myquestions(
        @Header("Authorization") token : String,
    ):Response<PostResponse>
}