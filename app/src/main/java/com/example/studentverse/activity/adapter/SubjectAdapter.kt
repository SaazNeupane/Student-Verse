package com.example.studentverse.activity.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.studentverse.R
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.model.Subject
import com.example.studentverse.activity.repository.SubjectRepository
import com.example.studentverse.activity.ui.SinglePostActivity
import com.example.studentverse.activity.ui.TopicActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SubjectAdapter (
    private val listsubject: ArrayList<Subject>,
    private val context: Context
        ):RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>(){

    class SubjectViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val subjectimage: ImageView = view.findViewById(R.id.adimage)
        val llsubject: LinearLayout = view.findViewById(R.id.llsubject)

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubjectAdapter.SubjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.studymaterialdesign,parent,false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectAdapter.SubjectViewHolder, position: Int) {
        val subject = listsubject[position]
        val imagepath= ServiceBuilder.loadImagepath()+subject.pictureName
        Glide.with(context)
            .load(imagepath)
            .apply( RequestOptions()
                .placeholder(R.drawable.blogo)
                .fitCenter())
            .into(holder.subjectimage)

        holder.llsubject.setOnClickListener {
            val intent = Intent(context, TopicActivity::class.java)
                .putExtra("topic",subject)
            context.startActivity(intent)
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val subjectRepository = SubjectRepository()
                val response = subjectRepository.subjectsimage(subject.pictureName!!)

                if (response.success == true) {
                    withContext(Dispatchers.Main) {
                        Glide.with(context)
                            .load(imagepath)
                            .apply( RequestOptions()
                                .placeholder(R.drawable.blogo)
                                .fitCenter())
                            .into(holder.subjectimage)

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
    }

    override fun getItemCount(): Int {
        return listsubject.size
    }

}