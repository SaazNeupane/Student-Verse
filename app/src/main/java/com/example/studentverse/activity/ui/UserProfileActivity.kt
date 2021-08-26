package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.model.User

class UserProfileActivity : AppCompatActivity() {

    private lateinit var name:TextView
    private lateinit var username:TextView
    private lateinit var mobile:TextView
    private lateinit var email:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        username = findViewById(R.id.visitusername)
        mobile = findViewById(R.id.vmobile)
        email = findViewById(R.id.vemail)
        name = findViewById(R.id.vname)

        val intent = intent.getParcelableExtra<User>("suser")!!

        username.text = intent.username
        name.text = "${intent.fname} ${intent.lname}"
        mobile.text = "Mobile: ${intent.mobile}"
        email.text = "Email: ${intent.email}"
    }
}