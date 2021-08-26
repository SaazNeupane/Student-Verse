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

    //All question
    suspend fun allquestion(): PostResponse {
        return apiRequest {
            postAPI.post(ServiceBuilder.token!!)
        }
    }

    //Add Question
    suspend fun addpost(post: Post):AddPostResponse{
        return apiRequest {
            postAPI.addAd(
                ServiceBuilder.token!!,post
            )
        }
    }

    //Update Post
    suspend fun updatepost(id:String, title:String, body:String, tags:List<String> ):PostResponse{
        return apiRequest {
            postAPI.updatepost(
                ServiceBuilder.token!!,id,title,body,tags
            )
        }
    }

    //Delete Post
    suspend fun deletepost(id:String):PostResponse{
        return apiRequest {
            postAPI.deletepost(
                ServiceBuilder.token!!,id
            )
        }
    }

    //Single question
    suspend fun singlepost(id: String): ViewResponse {
        return apiRequest {
            postAPI.singlepost(id)
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

    //DownVote
    suspend fun downvote(vote: Vote) : VoteResponse {
        return apiRequest {
            postAPI.downvote(
                ServiceBuilder.token!!,vote
            )
        }
    }

    //Unvote
    suspend fun unvote(vote: Vote) : VoteResponse {
        return apiRequest {
            postAPI.unvote(
                ServiceBuilder.token!!,vote
            )
        }
    }

    //Seacrh Question
    suspend fun searchquestion(text:String): PostResponse {
        return apiRequest {
            postAPI.searchquestion(text)
        }
    }

    //Seacrh Tags
    suspend fun searchtag(text:String): PostResponse {
        return apiRequest {
            postAPI.searchtag(text)
        }
    }

    //User Own Question
    suspend fun myquestions(): PostResponse {
        return apiRequest {
            postAPI.myquestions(ServiceBuilder.token!!)
        }
    }

    //Other's question
    suspend fun otherpost(id: String): PostResponse {
        return apiRequest {
            postAPI.otherpost(ServiceBuilder.token!!,id)
        }
    }
}