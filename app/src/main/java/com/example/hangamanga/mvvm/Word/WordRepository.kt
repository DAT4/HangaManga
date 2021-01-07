package com.example.hangamanga.mvvm.Word

import com.example.hangamanga.api.RetrofitInstance

class WordRepository(private val db: WordDatabase) {
    fun getWords() = RetrofitInstance.api.getWords()
}

