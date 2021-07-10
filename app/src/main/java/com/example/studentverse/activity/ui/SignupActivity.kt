package com.example.studentverse.activity.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
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

class SignupActivity : AppCompatActivity() {
    private lateinit var etfname: EditText
    private lateinit var etlname: EditText
    private lateinit var etmobile: EditText
    private lateinit var etaddress: EditText
    private lateinit var etemail: EditText
    private lateinit var etusername: EditText
    private lateinit var etpassword: EditText
    private lateinit var etrepassword: EditText
    private lateinit var btnsignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        etfname = findViewById(R.id.etfname)
        etlname = findViewById(R.id.etlname)
        etmobile = findViewById(R.id.etmobile)
        etaddress = findViewById(R.id.etaddress)
        etemail = findViewById(R.id.etemail)
        etusername = findViewById(R.id.etusername)
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
        val address = etaddress.text.toString()
        val email = etemail.text.toString()
        val username = etusername.text.toString()
        val password = etpassword.text.toString()
        val repassword = etrepassword.text.toString()

        if(password != repassword){
            etrepassword.error = "Password does not match"
            etrepassword.requestFocus()
        }
        else{
            val user = User(fname = fname,lname = lname, mobile = mobile, address=address, email = email, username=username, password = password)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val userRepository = UserRepository()
                    val response = userRepository.userRegister(user)
                    if (response.success == true){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@SignupActivity, "${response.message}", Toast.LENGTH_SHORT).show()
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
        else if(TextUtils.isEmpty(etaddress.text)){
            etaddress.setError("Please enter your Address")
            etaddress.requestFocus()
            flag = false
        }
        else if(TextUtils.isEmpty(etemail.text)){
            etemail.setError("Please enter your Email Address")
            etemail.requestFocus()
            flag = false
        }
        else if(TextUtils.isEmpty(etusername.text)){
            etusername.setError("Please enter your Username")
            etusername.requestFocus()
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
        etaddress.text.clear()
        etemail.text.clear()
        etusername.text.clear()
        etpassword.text.clear()
        etrepassword.text.clear()
    }
}