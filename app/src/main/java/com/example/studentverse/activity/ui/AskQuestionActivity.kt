package com.example.studentverse.activity.ui

import android.R.attr.y
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentverse.R
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.repository.QuestionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AskQuestionActivity : AppCompatActivity() {
    private lateinit var ettitle: EditText
    private lateinit var etbody: EditText
    private lateinit var ettags: EditText
    private lateinit var btnpost: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_question)

        ettitle = findViewById(R.id.ettitle)
        etbody = findViewById(R.id.etbody)
        ettags = findViewById(R.id.ettags)
        btnpost = findViewById(R.id.btnpost)

        btnpost.setOnClickListener {
            if (checkEmpty()){
                addpost()
            }
        }
    }

    private fun addpost() {
        val title = ettitle.text.toString()
        val body = etbody.text.toString()
        val tag = ettags.text.toString()
        val tags: List<String> = tag.trim().splitToSequence(",").filter { it.isNotEmpty() }.toList()

        val post = Post(title = title, body = body, tags = tags)

        CoroutineScope(Dispatchers.IO).launch {
            try{
                val questionRepository = QuestionRepository()
                val response = questionRepository.addpost(post)
                if (response.success == true){
                        withContext(Dispatchers.Main){
                            Toast.makeText(this@AskQuestionActivity, "${response.message}", Toast.LENGTH_SHORT).show()
                            startActivity(
                                Intent(
                                    this@AskQuestionActivity,
                                    DashboardActivity::class.java
                                )
                            )
                        }
                }
            }
            catch (ex: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@AskQuestionActivity,
                        ex.toString(),
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun checkEmpty(): Boolean{
        var flag=true

        if(TextUtils.isEmpty(ettitle.text)){
            ettitle.setError("Please enter Post Title")
            ettitle.requestFocus()
            flag = false
        }
        else if(TextUtils.isEmpty(etbody.text)){
            etbody.setError("Please enter something on body")
            etbody.requestFocus()
            flag = false
        }
        else if(TextUtils.isEmpty(ettags.text)){
            ettags.setError("Please enter tags")
            ettags.requestFocus()
            flag = false
        }
        return flag
    }
}