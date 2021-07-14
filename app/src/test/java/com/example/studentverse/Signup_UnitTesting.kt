package com.example.studentverse

import com.example.studentverse.activity.model.User
import com.example.studentverse.activity.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class Signup_UnitTesting {
    private lateinit var userRepository: UserRepository
    @Test
    fun registerUser() = runBlocking {
        userRepository = UserRepository()
        val user =
            User(fname = "abc", lname = "xyz", email = "abc@gmail.com", username = "abc", mobile = "9898", password = "abc", address = "abc")

        val response = userRepository.userRegister(user)
        val expectedResult = true
        val actualResult = response.success
        Assert.assertEquals(expectedResult, actualResult)
    }

}