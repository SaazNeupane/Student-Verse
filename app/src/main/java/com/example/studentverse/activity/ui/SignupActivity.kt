package com.example.studentverse.activity.ui

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.studentverse.R
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.notification.NotificationChannels
import com.example.studentverse.activity.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class SignupActivity : AppCompatActivity() {
    private lateinit var etfname: EditText
    private lateinit var etlname: EditText
    private lateinit var etmobile: EditText
    private lateinit var etemail: EditText
    private lateinit var etpassword: EditText
    private lateinit var etrepassword: EditText
    private lateinit var btnsignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etfname = findViewById(R.id.etfname)
        etlname = findViewById(R.id.etlname)
        etmobile = findViewById(R.id.etmobile)
        etemail = findViewById(R.id.etemail)
        etpassword = findViewById(R.id.etpassword)
        etrepassword = findViewById(R.id.etrepassword)
        btnsignup = findViewById(R.id.btnsignup)

        btnsignup.setOnClickListener {
            if (checkEmpty()) {
                signup()
            }
        }
    }
    private fun signup(){
        val fname = etfname.text.toString()
        val lname = etlname.text.toString()
        val mobile = etmobile.text.toString()
        val email = etemail.text.toString()
        val password = etpassword.text.toString()
        val repassword = etrepassword.text.toString()

        if(password != repassword){
            etrepassword.error = "Password does not match"
            etrepassword.requestFocus()
        }
        else{
            val user = User(fname = fname,lname = lname, mobile = mobile, email = email, password = password)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val userRepository = UserRepository()
                    val response = userRepository.userRegister(user)

                    if (response.success == true){
                        withContext(Dispatchers.Main){
                            showNotification("${response.message}")
                            reset()
                            startActivity(
                                Intent(
                                    this@SignupActivity,
                                    LoginActivity::class.java
                                )
                            )
                            finish()
                        }
                    }
                    if (response.success == false){
                        withContext(Dispatchers.Main){
                            println(response.error?.get(0)?.msg.toString())
                            println(response.error)
                            Toast.makeText(this@SignupActivity, (response.error?.get(0)?.msg), Toast.LENGTH_SHORT).show()
                        }
                    }

                } catch (e: Exception){

                    withContext(Dispatchers.Main){
                        Toast.makeText(this@SignupActivity, "$e", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun checkEmpty(): Boolean{
        var flag=true

        if(TextUtils.isEmpty(etfname.text)){
            etfname.setError("Please enter your First Name")
            etfname.requestFocus()
            flag = false
        }

        if(TextUtils.isEmpty(etlname.text)){
            etlname.setError("Please enter your Last Name")
            etlname.requestFocus()
            flag = false
        }
        else if(TextUtils.isEmpty(etmobile.text)){
            etmobile.setError("Please enter your Mobile Number")
            etmobile.requestFocus()
            flag = false
        }
        else if(TextUtils.isEmpty(etemail.text)){
            etemail.setError("Please enter your Email Address")
            etemail.requestFocus()
            flag = false
        }
        else if(TextUtils.isEmpty(etpassword.text)){
            etpassword.setError("Please Enter a valid password")
            etpassword.requestFocus()
            flag = false
        }
        return flag
    }

    private fun reset(){
        etfname.text.clear()
        etlname.text.clear()
        etmobile.text.clear()
        etemail.text.clear()
        etpassword.text.clear()
        etrepassword.text.clear()
    }

    private fun showNotification(message: String) {

        val notificationManager = NotificationManagerCompat.from(this)

        val notificationChannels = NotificationChannels(this)
        notificationChannels.createNotificationChannels()

        val notification = NotificationCompat.Builder(this, notificationChannels.CHANNEL_1)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Student-Verse")
            .setContentText("$message")
            .setColor(Color.BLUE)
            .build()

        notificationManager.notify(1, notification)

    }
}