package com.example.studentverse.activity.repository

import com.example.studentverse.activity.api.APIRequest
import com.example.studentverse.activity.api.PostAPI
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.api.UserAPI
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.response.AddPostResponse
import com.example.studentverse.activity.response.PostResponse

class QuestionRepository:APIRequest() {
    private val postAPI = ServiceBuilder.buildService(PostAPI::class.java)

    //question
    suspend fun allquestion(): PostResponse {
        return apiRequest {
            postAPI.post(ServiceBuilder.token!!)
        }
    }

    //Add Ad
    suspend fun addpost(post: Post):AddPostResponse{
        return apiRequest {
            postAPI.addAd(
                ServiceBuilder.token!!,post
            )
        }
    }
}