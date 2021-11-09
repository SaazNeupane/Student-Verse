package com.example.studentverse.activity.api

import android.view.View
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

    //Update Post
    @FormUrlEncoded
    @PUT("post/update")
    suspend fun updatepost(
        @Header("Authorization") token : String,
        @Field("id") id: String,
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("tags") tags: List<String>,
    ): Response<PostResponse>

    //delete post
    @DELETE("post/{id}")
    suspend fun deletepost(
        @Header("Authorization") token : String,
        @Path("id") id: String
    ):Response<PostResponse>

    //get post
    @GET("posts")
    suspend fun post(
        @Header("Authorization") token : String,
    ):Response<PostResponse>

    //get single post
    @GET("post/{id}")
    suspend fun singlepost(
        @Path("id") id: String,
    ):Response<ViewResponse>

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

    //Up Voting
    @POST("upvote")
    suspend fun upvote(
        @Header("Authorization") token : String,
        @Body vote: Vote
    ): Response<VoteResponse>

    //DownVote
    @POST("downvote")
    suspend fun downvote(
        @Header("Authorization") token : String,
        @Body vote: Vote
    ): Response<VoteResponse>

    //Unvote
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

    //get other post
    @GET("/otheruser/post/{id}")
    suspend fun otherpost(
        @Header("Authorization") token : String,
        @Path("id") id: String,
    ):Response<PostResponse>

    //get my score
    @GET("/score")
    suspend fun myscore(
        @Header("Authorization") token : String
    ):Response<FetchScoreResponse>
}