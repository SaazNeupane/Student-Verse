package com.example.studentverse.activity.response

import com.example.studentverse.activity.model.Answer

class AnswerResponse (
    val success : Boolean?=null,
    val message: String? = null,
    val data : ArrayList<Answer>? = null
)