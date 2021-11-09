package com.example.studentverse.activity.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.studentverse.R
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.system.exitProcess

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var currentPassword: EditText
    private lateinit var newPassword: EditText
    private lateinit var renewPassword: EditText
    private lateinit var btnchange: Button

    private var userDetails: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        currentPassword = findViewById(R.id.etcurrpassword)
        newPassword = findViewById(R.id.etnewpassword)
        renewPassword = findViewById(R.id.etrenewpassword)
        btnchange = findViewById(R.id.btnchangepassword)

        btnchange.setOnClickListener {
            update()
        }
    }

    private fun update(){
        if(newPassword.text.toString() != renewPassword.text.toString()){
            renewPassword.error = "Password does not match"
            renewPassword.requestFocus()
        }
        else {
            val password = currentPassword.text.toString()
            val newpassword = newPassword.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val userRepository = UserRepository()
                    val response = userRepository.changepassword(password,newpassword)

                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@ChangePasswordActivity, "${response.message}", Toast.LENGTH_SHORT).show()
                            startActivity(
                                Intent(
                                    this@ChangePasswordActivity,
                                    DashboardActivity::class.java
                                )
                            )
                        }
                    }
                } catch (ex: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@ChangePasswordActivity,
                            "Error : $ex", Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

}