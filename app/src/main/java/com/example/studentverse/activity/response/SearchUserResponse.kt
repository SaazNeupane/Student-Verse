package com.example.studentverse.activity.response

import com.example.studentverse.activity.model.User


class SearchUserResponse (
    val success: Boolean?=null,
    val data: ArrayList<User>? = null
)