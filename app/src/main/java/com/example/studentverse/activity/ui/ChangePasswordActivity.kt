package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.studentverse.R
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

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
        loadclient()

        btnchange.setOnClickListener {
            update()
        }
    }

    private fun loadclient() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val response = userRepository.profile()

                if (response.success == true) {
                    userDetails = response.data!!
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            this@ChangePasswordActivity,
                            "${userDetails!!.password}", Toast.LENGTH_LONG
                        ).show()
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

    private fun update(){
        Toast.makeText(this, "${userDetails?.fname}", Toast.LENGTH_SHORT).show()
        if(newPassword != renewPassword){
            renewPassword.error = "Password does not match"
            renewPassword.requestFocus()
        }
        else {
        }
    }

}