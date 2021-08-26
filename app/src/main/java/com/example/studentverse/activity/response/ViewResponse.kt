package com.example.studentverse.activity.response

import com.example.studentverse.activity.model.Post

class ViewResponse (
    val success: Boolean?=null,
    val data: Post? = null,
    val message: String? =null
)