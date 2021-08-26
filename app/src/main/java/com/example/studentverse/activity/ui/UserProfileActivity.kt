package com.example.studentverse.activity.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.adapter.QuestionAdapter
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.repository.QuestionRepository
import com.example.studentverse.activity.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserProfileActivity : AppCompatActivity() {

    private lateinit var name:TextView
    private lateinit var username:TextView
    private lateinit var mobile:TextView
    private lateinit var email:TextView
    private lateinit var ntf:TextView
    private lateinit var rvotherques:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        username = findViewById(R.id.visitusername)
        mobile = findViewById(R.id.vmobile)
        email = findViewById(R.id.vemail)
        ntf = findViewById(R.id.ntf)
        name = findViewById(R.id.vname)
        rvotherques = findViewById(R.id.rvotherques)

        val intent = intent.getParcelableExtra<User>("suser")!!

        username.text = intent.username
        name.text = "${intent.fname} ${intent.lname}"
        mobile.text = "Mobile: ${intent.mobile}"
        email.text = "Email: ${intent.email}"

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val postRepository = QuestionRepository()
                val response = postRepository.otherpost(intent._id!!)

                if (response.success == true) {
                    val post = response.data!!
                    withContext(Dispatchers.Main) {
                        ntf.visibility = View.GONE
                        rvotherques.visibility = View.VISIBLE
                        val questionAdapter = QuestionAdapter(this@UserProfileActivity,post)
                        rvotherques.adapter = questionAdapter
                        rvotherques.layoutManager= LinearLayoutManager(this@UserProfileActivity, LinearLayoutManager.VERTICAL,false)
                    }
                }
            } catch (ex: java.lang.Exception) {
                withContext(Dispatchers.Main){
                    rvotherques.visibility = View.GONE
                    ntf.visibility = View.VISIBLE
                }
            }
        }


    }
}