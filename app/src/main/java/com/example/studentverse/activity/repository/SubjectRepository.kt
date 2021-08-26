package com.example.studentverse.activity.repository

import com.example.studentverse.activity.api.APIRequest
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.api.SubjectAPI
import com.example.studentverse.activity.response.*

class SubjectRepository:APIRequest() {
    private val subjectAPI = ServiceBuilder.buildService(SubjectAPI::class.java)

    //All Subjects
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

    //Get Chapter
    suspend fun getchapter(sid:String,tid: String): ChapterResponse {
        return apiRequest {
            subjectAPI.getchapter(sid,tid)
        }
    }

    //Get Quiz
    suspend fun getquiz(id:String): QuizResponse {
        return apiRequest {
            subjectAPI.getquiz(id)
        }
    }

    //Add quiz score
    suspend fun addscore(score:String, quizname:String,time: String):ScoreResponse{
        return apiRequest {
            subjectAPI.checkclient(ServiceBuilder.token!!,score,quizname,time)
        }
    }
}