package com.example.studentverse.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Post


class QuestionAdapter(
    private val context: Context,
    private val PostList: ArrayList<Post>

):
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>(){
    class QuestionViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val createdAt: TextView = view.findViewById(R.id.createdAt)
        val comment: TextView = view.findViewById(R.id.comment)
        val title: TextView = view.findViewById(R.id.title)
        val tags: TextView = view.findViewById(R.id.tags)
        val views: TextView = view.findViewById(R.id.views)
        val author: TextView = view.findViewById(R.id.author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_dashboard,parent,false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val post = PostList[position]
//        holder.createdAt.text=post.createdAt
//        holder.comment.text=post.comment
        holder.title.text=post.title
        holder.tags.text=post.tags
//        holder.views.text=post.views
        holder.author.text=post.author

    }

    override fun getItemCount(): Int {
        return PostList.size
    }

}