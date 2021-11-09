package com.example.studentverse.activity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.model.Paper

class PastAdapter (
    private val listpaper: ArrayList<Paper>,
    private val context: Context,
): RecyclerView.Adapter<PastAdapter.PastHolder>() {
    class PastHolder(view: View): RecyclerView.ViewHolder(view){

        val tvquestion: TextView = view.findViewById(R.id.tvpaperquestion)
        val tvyear: TextView = view.findViewById(R.id.tvpastyear)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastAdapter.PastHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.paperdesign, parent,false)
        return PastAdapter.PastHolder(view)
    }

    override fun onBindViewHolder(holder: PastAdapter.PastHolder, position: Int ) {
        val paper = listpaper[position]

        holder.tvquestion.text = paper.question
        holder.tvyear.text = paper.year

    }

    override fun getItemCount(): Int {
           return listpaper.size
    }
}