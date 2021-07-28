package com.example.studentverse.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Answer
import com.example.studentverse.activity.model.Comment

class CommentAdapter (
    private val listcomment: ArrayList<Comment>,
    private val context: Context
        ) : RecyclerView.Adapter<CommentAdapter.CommentHolder>() {
    class CommentHolder(view: View) : RecyclerView.ViewHolder(view){
        val comment: TextView = view.findViewById(R.id.tvccomment)
        val user: TextView = view.findViewById(R.id.tvcuser)
    }

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

    }

    override fun getItemCount(): Int {
        return listcomment.size
    }
}