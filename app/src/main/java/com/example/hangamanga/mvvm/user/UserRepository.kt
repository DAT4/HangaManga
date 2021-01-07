package com.example.hangamanga.mvvm.user

import com.example.hangamanga.api.RetrofitInstance
import com.example.hangamanga.models.User

class UserRepository(private val db: UserDatabase) {
    suspend fun getToken(user: User) = RetrofitInstance.api.login(user)
}

