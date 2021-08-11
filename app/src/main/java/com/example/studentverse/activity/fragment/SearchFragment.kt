package com.example.studentverse.activity.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.studentverse.R

class SearchFragment : Fragment() {
    private lateinit var spoptions: Spinner
    private lateinit var btnsearch: Button
    private var selectedItem= ""
    private val options= arrayOf(
        "Questions","Tags","User"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_search, container, false)

        spoptions = view.findViewById(R.id.spoptions)

        spoptions.adapter = ArrayAdapter(
            context!!,
            R.layout.support_simple_spinner_dropdown_item,options
        )

        spoptions.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long) {
                    selectedItem = parent?.getItemAtPosition(position).toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }


        return view
    }
}