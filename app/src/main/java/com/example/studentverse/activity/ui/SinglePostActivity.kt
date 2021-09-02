package com.example.studentverse.activity.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.studentverse.R
import com.example.studentverse.activity.adapter.AnswerAdapter
import com.example.studentverse.activity.model.Answer
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.repository.QuestionRepository
import com.example.studentverse.activity.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class SinglePostActivity : AppCompatActivity() {
    private lateinit var stitle: TextView
    private lateinit var stime: TextView
    private lateinit var sviews: TextView
    private lateinit var stags: TextView
    private lateinit var suser: TextView
    private lateinit var sbody: TextView
    private lateinit var sanswercount: TextView
    private lateinit var etanswer: EditText
    private lateinit var btnanswer: Button
    private lateinit var rvanswer: RecyclerView
    private lateinit var llanswer: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_post)

        stitle=findViewById(R.id.stitle)
        stime=findViewById(R.id.stime)
        sviews=findViewById(R.id.sviews)
        stags=findViewById(R.id.stags)
        suser=findViewById(R.id.suser)
        sbody=findViewById(R.id.sbody)
        etanswer=findViewById(R.id.etanswer)
        btnanswer=findViewById(R.id.btnanswer)
        rvanswer=findViewById(R.id.rvanswer)
        llanswer=findViewById(R.id.llanswer)
        sanswercount=findViewById(R.id.sanswercount)

        val intent = intent.getParcelableExtra<Post>("post")!!

        sviews.text = "Viewed: ${intent.views} times"

        val tags = intent.tags.toString()
            .replace("[", "")
            .replace("]", "")

        stags.text = "Tags: $tags"

        if(intent !=null){
            stitle.text = "${intent.title}"
            val time = intent.createdAt
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val parsedDate: Date = dateFormat.parse(time)
            val print = SimpleDateFormat("d MMM, yyyy")
            stime.text = "Asked: ${print.format(parsedDate)}"
            sbody.text = "${intent.body}"

        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val response = userRepository.profile()

                if (response.success == true) {
                    val userDetails = response.data!!
                    withContext(Dispatchers.Main) {
                        if (intent.author.toString() == userDetails._id.toString()){
                            llanswer.visibility = View.GONE
                        }
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@SinglePostActivity,
                        "Error : $ex", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val response = userRepository.finduser(intent.author!!)

                if (response.success == true) {
                    val userDetails = response.data!!
                    withContext(Dispatchers.Main) {
                        suser.text = "Asked by: ${userDetails!!.username.toString()}"
                    }
                }
            } catch (ex: java.lang.Exception) {
                withContext(Dispatchers.Main){
                    Toast.makeText(this@SinglePostActivity,
                        ex.toString(),
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        btnanswer.setOnClickListener {

            if (checkEmpty()) {
                val ans = etanswer.text.toString()
                val answer = Answer(post = intent._id, text = ans)

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val questionRepository = QuestionRepository()
                        val response = questionRepository.answeradd(answer)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    this@SinglePostActivity,
                                    "Answer Added",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                                val intent =
                                    Intent(this@SinglePostActivity, SinglePostActivity::class.java)
                                        .putExtra("post", intent)
                                startActivity(intent)
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                this@SinglePostActivity,
                                ex.toString(),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val questionRepository = QuestionRepository()
                val response = questionRepository.getanswer(intent._id!!)
                if (response.success == true) {
                    val answer = response.data!!
                    withContext(Dispatchers.Main) {
                        sanswercount.text = "${answer.size.toString()} Answers"
                        val answerAdapter = AnswerAdapter(answer,intent, this@SinglePostActivity)
                        rvanswer.adapter = answerAdapter
                        rvanswer.layoutManager= LinearLayoutManager(this@SinglePostActivity, LinearLayoutManager.VERTICAL,false)
                    }
                }

            }
            catch (ex:Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SinglePostActivity,
                        "$ex , Here",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    private fun checkEmpty(): Boolean{
        var flag=true

        if(TextUtils.isEmpty(etanswer.text)){
            etanswer.error = "Please answer the question"
            etanswer.requestFocus()
            flag = false
        }
        return flag
    }
}