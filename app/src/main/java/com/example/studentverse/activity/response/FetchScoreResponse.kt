package com.example.studentverse.activity.response

import com.example.studentverse.activity.model.Score

class FetchScoreResponse (
    val success: Boolean?=true,
    val data: ArrayList<Score>? =null,
    val message:String? = null
)