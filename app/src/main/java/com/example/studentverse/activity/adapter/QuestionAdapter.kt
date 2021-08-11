package com.example.studentverse.activity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Post
import com.example.studentverse.activity.ui.SinglePostActivity


class QuestionAdapter(
    private val context: Context,
    private val PostList: MutableList<Post>

):
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>(){
    class QuestionViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val comment: TextView = view.findViewById(R.id.answercount)
        val title: TextView = view.findViewById(R.id.title)
        val body: TextView = view.findViewById(R.id.body)
        val tags: TextView = view.findViewById(R.id.tags)
        val answercount: TextView = view.findViewById(R.id.answercount)
        val llbutton: LinearLayout = view.findViewById(R.id.llbutton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_dashboard,parent,false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val post = PostList[position]
        holder.title.text=post.title
        holder.body.text=post.body
        val tags = post.tags.toString()
            .replace("[", "")
            .replace("]", "")
        holder.tags.text= "Tags: $tags"

        holder.llbutton.setOnClickListener {
            val intent = Intent(context, SinglePostActivity::class.java)
                .putExtra("post",post)
            context.startActivity(intent)
        }
        holder.answercount.text = post.answer?.size.toString()

    }

    override fun getItemCount(): Int {
        return PostList.size
    }
}