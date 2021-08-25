package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.studentverse.R

class ChapterContentActivity : AppCompatActivity() {
    private lateinit var cctitle: TextView
    private lateinit var ccbody: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_content)

        cctitle = findViewById(R.id.cctitle)
        ccbody = findViewById(R.id.ccbody)
        
    }
}