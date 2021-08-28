package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Chapter

class ChapterContentActivity : AppCompatActivity() {
    private lateinit var cctitle: TextView
    private lateinit var ccbody: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_content)

        cctitle = findViewById(R.id.cctitle)
        ccbody = findViewById(R.id.ccbody)

        val content = intent.getParcelableExtra<Chapter>("content")!!

        cctitle.text = content.name

        ccbody.text = content.content


    }
}