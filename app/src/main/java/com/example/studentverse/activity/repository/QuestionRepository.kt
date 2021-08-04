package com.example.studentverse.activity.repository

import com.example.studentverse.activity.api.APIRequest
import com.example.studentverse.activity.api.PostAPI
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.api.UserAPI
import com.example.studentverse.activity.model.Answer
import com.example.studentverse.activity.model.Comment
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.model.Vote
import com.example.studentverse.activity.response.*

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

    //Add Answer
    suspend fun answeradd(answer: Answer): AnswerAddResponse {
        return apiRequest {
            postAPI.answeradd(
                ServiceBuilder.token!!,answer
            )
        }
    }

    //Get Answer
    suspend fun getanswer(id: String): AnswerResponse {
        return apiRequest {
            postAPI.getanswer(id)
        }
    }

    //Add Comment
    suspend fun addcomment(comment: Comment) : CommentAddResponse {
        return apiRequest {
            postAPI.addcomment(
                ServiceBuilder.token!!,comment
            )
        }
    }

    //Voting
    suspend fun upvote(vote : Vote) : VoteResponse {
        return apiRequest {
            postAPI.upvote(
                ServiceBuilder.token!!,vote
            )
        }
    }

    suspend fun downvote(vote: Vote) : VoteResponse {
        return apiRequest {
            postAPI.downvote(
                ServiceBuilder.token!!,vote
            )
        }
    }

    suspend fun unvote(vote: Vote) : VoteResponse {
        return apiRequest {
            postAPI.unvote(
                ServiceBuilder.token!!,vote
            )
        }
    }
}