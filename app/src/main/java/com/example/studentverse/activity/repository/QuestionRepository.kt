package com.example.studentverse.activity.repository

import com.example.studentverse.activity.api.APIRequest
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.api.UserAPI
import com.example.studentverse.activity.response.PostResponse

class QuestionRepository:APIRequest() {
    private val userAPI = ServiceBuilder.buildService(UserAPI::class.java)

    //question
    suspend fun allquestion(): PostResponse {
        return apiRequest {
            userAPI.post(ServiceBuilder.token!!)
        }
    }
}