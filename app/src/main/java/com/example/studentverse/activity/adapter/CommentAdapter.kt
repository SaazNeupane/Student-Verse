package com.example.studentverse.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Answer
import com.example.studentverse.activity.model.Comment
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CommentAdapter (
    private val listcomment: ArrayList<Comment>,
    private val context: Context
        ) : RecyclerView.Adapter<CommentAdapter.CommentHolder>() {
    class CommentHolder(view: View) : RecyclerView.ViewHolder(view){
        val comment: TextView = view.findViewById(R.id.tvccomment)
        val user: TextView = view.findViewById(R.id.tvcuser)
        val ctime: TextView = view.findViewById(R.id.ctime)
    }
    private var userDetails: User? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentAdapter.CommentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_comment, parent,false)
        return CommentAdapter.CommentHolder(view)
    }

    override fun onBindViewHolder(holder: CommentAdapter.CommentHolder, position: Int) {
        val cmt = listcomment[position]
        holder.comment.text= cmt.text
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val response = userRepository.finduser(cmt.author!!)

                if (response.success == true) {
                    userDetails = response.data!!
                    withContext(Dispatchers.Main) {
                        holder.user.text = "- ${userDetails!!.username.toString()}"
                    }
                }
            } catch (ex: java.lang.Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Error : $ex", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val parsedDate: Date = dateFormat.parse(cmt.createdAt)
        val print = SimpleDateFormat("MMM d, yyyy HH:mm")
        holder.ctime.text = "${print.format(parsedDate)}"

    }

    override fun getItemCount(): Int {
        return listcomment.size
    }
}