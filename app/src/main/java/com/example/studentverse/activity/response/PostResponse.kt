package com.example.studentverse.activity.response

import com.example.studentverse.activity.model.Post

class PostResponse (
    val success: Boolean?=null,
    val data: ArrayList<Post>? = null,
    val message: String? =null
)