package com.example.studentverse.activity.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.adapter.QuestionAdapter
import com.example.studentverse.activity.adapter.SubjectAdapter
import com.example.studentverse.activity.repository.QuestionRepository
import com.example.studentverse.activity.repository.SubjectRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudyMaterialFragment : Fragment() {
    private lateinit var rvsubjects: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_study_material, container, false)

        rvsubjects=view.findViewById(R.id.rvsubjects)
        loadsubjects()

        return view
    }

    private fun loadsubjects(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val subjectRepository = SubjectRepository()
                val response = subjectRepository.allsubjects()
                if (response.success == true) {
                    val subject = response.data!!
                    withContext(Dispatchers.Main) {
                        val subjectAdapter = SubjectAdapter(subject,context!!)
                        rvsubjects.adapter = subjectAdapter
                        rvsubjects.layoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                    }
                }
            }
            catch (ex:Exception){
                withContext(Dispatchers.Main) {
                    Toast.makeText(context,
                        "$ex .toString()",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}