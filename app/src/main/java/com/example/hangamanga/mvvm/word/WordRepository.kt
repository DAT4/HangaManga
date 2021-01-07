package com.example.hangamanga.mvvm.word

import com.example.hangamanga.api.RetrofitInstance

class WordRepository(private val db: WordDatabase) {
    suspend fun getWords() = RetrofitInstance.api.getWords()
}

