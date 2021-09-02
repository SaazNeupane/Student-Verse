package com.example.studentverse.activity.response

import com.example.studentverse.activity.model.Quiz

class QuizResponse (
    val success: Boolean?=true,
    val data: ArrayList<Quiz>? = null
        )