package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.adapter.PastAdapter
import com.example.studentverse.activity.adapter.StudyQuizAdapter
import com.example.studentverse.activity.model.Chapter
import com.example.studentverse.activity.repository.SubjectRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class PastPaperActivity : AppCompatActivity() {

    private lateinit var rvpastpaper: RecyclerView
    private lateinit var tvpastpaper: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_paper)

        rvpastpaper=findViewById(R.id.rvpastpaper)
        tvpastpaper=findViewById(R.id.pastpapertopic)

        val chapter = intent.getParcelableExtra<Chapter>("pchapter")!!

        tvpastpaper.text = "Past paper of ${chapter.name}"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val subjectRepository = SubjectRepository()
                val response = subjectRepository.getpastpaper(chapter._id!!)
                if (response.success == true) {
                    val paper = response.data!!
                    withContext(Dispatchers.Main) {
                        val pastAdapter = PastAdapter(paper,this@PastPaperActivity)
                        rvpastpaper.adapter = pastAdapter
                        rvpastpaper.layoutManager= LinearLayoutManager(this@PastPaperActivity, LinearLayoutManager.VERTICAL,false)
                    }
                }

            }
            catch (ex: Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@PastPaperActivity,
                        "$ex, activity here",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}