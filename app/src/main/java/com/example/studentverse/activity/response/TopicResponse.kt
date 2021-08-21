package com.example.studentverse.activity.response

import com.example.studentverse.activity.model.Topic

class TopicResponse(
    val success: Boolean?=null,
    val data: ArrayList<Topic>? = null
)