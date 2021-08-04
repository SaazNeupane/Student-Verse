package com.example.studentverse.activity.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import com.example.studentverse.R
import com.example.studentverse.activity.ui.UpdateProfileActivity

class OthersFragment : Fragment() {
    private lateinit var update: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_others, container, false)

        update=view.findViewById(R.id.llupdate)

        update.setOnClickListener {
            startActivity(
                Intent(
                    context,
                    UpdateProfileActivity::class.java
                )
            )
        }

        return view
    }
}