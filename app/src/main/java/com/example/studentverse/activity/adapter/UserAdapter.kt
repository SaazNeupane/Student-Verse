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
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.ui.ChapterActivity

class UserAdapter(
    private val listuser: ArrayList<User>,
    private val context: Context
) : RecyclerView.Adapter<UserAdapter.UserHolder>() {
    class UserHolder(view: View) : RecyclerView.ViewHolder(view){
        val susername: TextView = view.findViewById(R.id.susername)
        val sname: TextView = view.findViewById(R.id.sfullname)
        val scount: TextView = view.findViewById(R.id.scount)
        val lluserbutton: LinearLayout = view.findViewById(R.id.lluserbutton)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.userdesign, parent,false)
        return UserAdapter.UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserHolder, position: Int) {
        val user = listuser[position]

        holder.susername.text = user.username
        holder.sname.text = "${user.fname} ${user.lname}"

        holder.lluserbutton.setOnClickListener {

        }


    }

    override fun getItemCount(): Int {
        return listuser.size

    }

}