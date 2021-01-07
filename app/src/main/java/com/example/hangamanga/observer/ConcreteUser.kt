package com.example.hangamanga.observer

import com.example.hangamanga.models.User
import java.lang.reflect.Type

object ConcreteUser : ICache<User?>{
    override val type: Type = User::class.java
    override val url: String = "https://mama.sh/hangman/api/login"
    override var content: User? = null
    override val observers: ArrayList<IObserver> = ArrayList()
}

