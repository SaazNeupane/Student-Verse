package com.example.studentverse.activity.repository

import com.example.studentverse.activity.api.APIRequest
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.api.SubjectAPI
import com.example.studentverse.activity.response.*

class SubjectRepository:APIRequest() {
    private val subjectAPI = ServiceBuilder.buildService(SubjectAPI::class.java)

    //Subjects
    suspend fun allsubjects(): SubjectResponse {
        return apiRequest {
            subjectAPI.subject(ServiceBuilder.token!!)
        }
    }

    //Get Topic
    suspend fun gettopic(id: String): TopicResponse {
        return apiRequest {
            subjectAPI.gettopic(id)
        }
    }

    //Get Topic
    suspend fun getchapter(sid:String,tid: String): ChapterResponse {
        return apiRequest {
            subjectAPI.getchapter(sid,tid)
        }
    }

    //Seacrh Question
    suspend fun searchquestion(text:String): PostResponse {
        return apiRequest {
            subjectAPI.searchquestion(text)
        }
    }

    //Seacrh Tags
    suspend fun searchtag(text:String): PostResponse {
        return apiRequest {
            subjectAPI.searchtag(text)
        }
    }
}