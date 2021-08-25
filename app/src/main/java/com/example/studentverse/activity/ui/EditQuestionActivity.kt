package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.studentverse.R

class EditQuestionActivity : AppCompatActivity() {
    private lateinit var ettitle: EditText
    private lateinit var etbody: EditText
    private lateinit var ettags: EditText
    private lateinit var btnpost: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_question)

        ettitle = findViewById(R.id.etedittitle)
        etbody = findViewById(R.id.eteditbody)
        ettags = findViewById(R.id.etedittags)
        btnpost = findViewById(R.id.btnupdatequestion)


    }
}