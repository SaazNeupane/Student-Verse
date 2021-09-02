package com.example.studentverse.activity.response

import com.example.studentverse.activity.model.Error

data class RegisterLoginResponse(
    val success:Boolean? = null,
    val message:String?,
    val token:String? = null,
    val error: ArrayList<Error>? = null,
)