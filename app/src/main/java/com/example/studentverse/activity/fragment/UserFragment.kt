package com.example.studentverse.activity.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentverse.R
import com.example.studentverse.activity.api.ServiceBuilder
import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.repository.UserRepository
import com.example.studentverse.activity.ui.AskQuestionActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class UserFragment : Fragment() {

    private lateinit var btnask: Button
    private lateinit var btnlogout: Button
    private lateinit var name: TextView
    private lateinit var mobile: TextView
    private lateinit var email: TextView
    private lateinit var profileimage: ImageView

    private var userDetails: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=  inflater.inflate(R.layout.fragment_user, container, false)

        btnask = view.findViewById(R.id.btnask)
        btnlogout = view.findViewById(R.id.btnlogout)
        name = view.findViewById(R.id.name)
        mobile = view.findViewById(R.id.mobile)
        email = view.findViewById(R.id.email)
        profileimage=view.findViewById(R.id.profileimage)

        btnask.setOnClickListener {
            startActivity(
                    Intent(
                            context,
                            AskQuestionActivity::class.java)
            )
        }

        btnlogout.setOnClickListener {
            val sharedPref = activity?.getSharedPreferences("Preferences", AppCompatActivity.MODE_PRIVATE)
            val editor = sharedPref?.edit()
            editor?.clear()
            editor?.apply()
            ServiceBuilder.token.equals("")
//            startActivity(
//                    Intent(
//                            context,
//                            LoginActivity::class.java
//                    )
//            )
            activity?.finish()
        }
        loadclient()

        return view
    }
    private fun loadclient() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userRepository = UserRepository()
                val response = userRepository.profile()

                if (response.success == true) {
                    userDetails = response.data!!
                    withContext(Dispatchers.Main) {
                        name.text = "Welcome, ${userDetails!!.fname}"
                        mobile.text = "Mobile: ${userDetails!!.mobile}"
                        email.text = "Email: ${userDetails!!.email}"
                    }
                }
            } catch (ex: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Error : $ex", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }


}