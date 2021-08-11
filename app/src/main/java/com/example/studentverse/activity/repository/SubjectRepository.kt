package com.example.studentverse.activity.repository

import com.example.studentverse.activity.api.APIRequest
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.api.SubjectAPI
import com.example.studentverse.activity.response.AnswerResponse
import com.example.studentverse.activity.response.SubjectResponse
import com.example.studentverse.activity.response.TopicResponse

class SubjectRepository:APIRequest() {
    private val subjectAPI = ServiceBuilder.buildService(SubjectAPI::class.java)

    //Subjects
    suspend fun allsubjects(): SubjectResponse {
        return apiRequest {
            subjectAPI.subject(ServiceBuilder.token!!)
        }
    }

    //Subjects Image
    suspend fun subjectsimage(pictureName:String): SubjectResponse {
        return apiRequest {
            subjectAPI.subjectimage(ServiceBuilder.token!!,pictureName)
        }
    }

    //Get Topic
    suspend fun gettopic(id: String): TopicResponse {
        return apiRequest {
            subjectAPI.gettopic(id)
        }
    }
}