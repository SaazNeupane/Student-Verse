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
import com.example.studentverse.activity.model.Topic
import com.example.studentverse.activity.ui.ChapterActivity

class TopicAdapter(
    private val listtopic: ArrayList<Topic>,
    private val subjectid: String,
    private val context: Context
) : RecyclerView.Adapter<TopicAdapter.TopicHolder>() {
    class TopicHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvtopicname: TextView = view.findViewById(R.id.tvtopicname)
        val tvtopicdesp: TextView = view.findViewById(R.id.tvtopicdesp)
        val lltopic: LinearLayout = view.findViewById(R.id.lltopic)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicAdapter.TopicHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.topicdesign, parent,false)
        return TopicAdapter.TopicHolder(view)
    }

    override fun onBindViewHolder(holder: TopicAdapter.TopicHolder, position: Int) {
        val topic = listtopic[position]

        holder.tvtopicname.text= "${topic.name}"
        holder.tvtopicdesp.text= topic.description

        holder.lltopic.setOnClickListener {
            val intent = Intent(context, ChapterActivity::class.java)
                .putExtra("topic",topic)
                .putExtra("subjectid",subjectid)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listtopic.size

    }

}