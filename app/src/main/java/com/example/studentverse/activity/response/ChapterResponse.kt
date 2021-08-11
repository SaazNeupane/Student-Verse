package com.example.studentverse.activity.response

import com.example.studentverse.activity.model.Chapter

class ChapterResponse (
    val success: Boolean?=true,
    val data: ArrayList<Chapter>? = null
        )