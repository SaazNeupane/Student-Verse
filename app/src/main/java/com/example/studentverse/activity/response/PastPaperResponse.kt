package com.example.studentverse.activity.response

import com.example.studentverse.activity.model.Paper

class PastPaperResponse (
    val success: Boolean?=true,
    val data: ArrayList<Paper>? = null,
    val count: Int? = 0
        )