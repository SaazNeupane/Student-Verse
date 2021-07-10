package com.example.studentverse.activity.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.example.studentverse.R
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {

    private lateinit var etusername: EditText
    private lateinit var textemail: TextInputLayout
    private lateinit var etpassword: EditText
    private lateinit var textpassword: TextInputLayout
    private lateinit var btnlogin: Button
    private lateinit var tvcreate: TextView
    private lateinit var cbremember: CheckBox
    private lateinit var lllogin: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etusername=findViewById(R.id.etlusername)
        textemail=findViewById(R.id.textemail)
        etpassword=findViewById(R.id.etlpassword)
        textpassword=findViewById(R.id.textpassword)
        btnlogin=findViewById(R.id.btnlogin)
        tvcreate=findViewById(R.id.tvcreate)
        cbremember=findViewById(R.id.cbremember)
        lllogin=findViewById(R.id.lllogin)

        btnlogin.setOnClickListener {
            if (checkEmpty()){
                login()
            }
        }

        tvcreate.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }

    private fun login() {
        val username = etusername.text.toString()
        val password = etpassword.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository = UserRepository()
                val response = repository.checkuser(username, password)
                if (response.success == true) {
                    ServiceBuilder.token = "Bearer " + response.token
                    saveSharedPref()
                    startActivity(
                        Intent(
                            this@LoginActivity,
                            MainActivity::class.java
                        )
                    )
                    finish()
                } else {
                    withContext(Dispatchers.Main) {
                        val snack =
                            Snackbar.make(
                                lllogin,
                                "${response.message}",
                                Snackbar.LENGTH_LONG
                            )
                        snack.setAction("OK", View.OnClickListener {
                            snack.dismiss()
                        })
                        snack.show()
                    }
                }

            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@LoginActivity,
                        "$ex", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }



    private fun checkEmpty(): Boolean {
        var flag = true

        if (TextUtils.isEmpty(etusername.text)) {
            etusername.setError("Please enter valid Username")
            etusername.requestFocus()
            flag = false
        } else if (TextUtils.isEmpty(etpassword.text)) {
            etpassword.setError("Please enter valid Password")
            etpassword.requestFocus()
            flag = false
        }
        return flag
    }

    private fun saveSharedPref() {
        val email = etusername.text.toString()
        val password = etpassword.text.toString()
        var remember = false
        if (cbremember.isChecked){
            remember = true;
        }
        val sharedPref = getSharedPreferences("Preferences", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.putBoolean("remember", remember)
        editor.apply()
    }
}