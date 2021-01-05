package com.example.hangamanga.models

import java.io.Serializable

data class Hategory(var title: String, val words: ArrayList<HighScore>) : Serializable
