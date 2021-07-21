package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.studentverse.R
import com.example.studentverse.activity.model.Post
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class SinglePostActivity : AppCompatActivity() {
    private lateinit var stitle: TextView
    private lateinit var stime: TextView
    private lateinit var sviews: TextView
    private lateinit var suser: TextView
    private lateinit var sbody: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_post)

        stitle=findViewById(R.id.stitle)
        stime=findViewById(R.id.stime)
        sviews=findViewById(R.id.sviews)
        suser=findViewById(R.id.suser)
        sbody=findViewById(R.id.sbody)

        val intent = intent.getParcelableExtra<Post>("post")

        if(intent !=null){
            stitle.setText("${intent.title}")
//            stime.setText("${intent.time}")
//            sviews.setText("${intent.sviews}")
            sbody.setText("${intent.body}")

        }

    }
}