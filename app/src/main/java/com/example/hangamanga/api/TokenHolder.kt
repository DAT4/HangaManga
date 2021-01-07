package com.example.hangamanga.api

import com.example.hangamanga.models.User

object TokenHolder {
    var token:String = ""
        get() = "Bearer $field"
    var user = User("n00b", "")
}