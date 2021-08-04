package com.example.studentverse.activity.response

import com.example.studentverse.activity.model.Post

class AddPostResponse (
    val success: Boolean?=null,
    val message: String?=null,
    val data: Post?= null
)