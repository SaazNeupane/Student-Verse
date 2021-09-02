package com.example.studentverse.activity.model

data class Quiz (
    val _id: String? =null,
    val name: String? = null,
    val question: String? = null,
    val options: ArrayList<String>? = null,
    val answer: String? = null,
    val chapter: String? =null
)