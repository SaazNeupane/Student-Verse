package com.example.studentverse.activity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Chapter
import com.example.studentverse.activity.ui.ChapterActivity
import com.example.studentverse.activity.ui.QuizActivity
import com.example.studentverse.activity.ui.SinglePostActivity

class ChapterAdapter (
    private val listchapter: ArrayList<Chapter>,
    private val context: Context
): RecyclerView.Adapter<ChapterAdapter.ChapterHolder>(){
    class ChapterHolder(view: View): RecyclerView.ViewHolder(view){
        val tvchaptername: TextView = view.findViewById(R.id.tvchaptername)
        val ivcontent: ImageView = view.findViewById(R.id.ivreadcontent)
        val ivquiz: ImageView = view.findViewById(R.id.ivtakequiz)
        val ivpast: ImageView = view.findViewById(R.id.ivpastpaper)
        val llchapter: LinearLayout = view.findViewById(R.id.llchapter)
        val llccontent: LinearLayout = view.findViewById(R.id.llccontent)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChapterAdapter.ChapterHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chapterdesign, parent,false)
        return ChapterAdapter.ChapterHolder(view)
    }

    override fun onBindViewHolder(holder: ChapterAdapter.ChapterHolder, position: Int) {
        val chapter = listchapter[position]

        holder.tvchaptername.text = "${chapter.name}"

        holder.llchapter.setOnClickListener {
            if (holder.llccontent.visibility == View.VISIBLE){
                holder.llccontent.visibility = View.GONE
            }
            else{
                holder.llccontent.visibility = View.VISIBLE
                holder.ivquiz.setOnClickListener {
                    val intent = Intent(context, QuizActivity::class.java)
                        .putExtra("chapter",chapter)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return listchapter.size
    }
}