package com.example.hangamanga.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.hangamanga.databinding.ActivityMainBinding
import com.example.hangamanga.mvvm.Score.ScoreDatabase
import com.example.hangamanga.mvvm.Score.ScoreRepository
import com.example.hangamanga.mvvm.Score.ScoreViewModel
import com.example.hangamanga.mvvm.Score.ScoreViewModelProviderFactory
import com.example.hangamanga.mvvm.Word.WordDatabase
import com.example.hangamanga.mvvm.Word.WordRepository
import com.example.hangamanga.mvvm.Word.WordViewModel
import com.example.hangamanga.mvvm.Word.WordViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var wordViewModel: WordViewModel
    lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Dependency Injection

        val wordRepository = WordRepository(WordDatabase(this))
        val wordViewModelProviderFactory = WordViewModelProviderFactory(wordRepository)
        wordViewModel = ViewModelProvider(this, wordViewModelProviderFactory).get(WordViewModel::class.java)

        val scoreRepository = ScoreRepository(ScoreDatabase(this))
        val scoreViewModelProviderFactory = ScoreViewModelProviderFactory(scoreRepository)
        scoreViewModel = ViewModelProvider(this, scoreViewModelProviderFactory).get(ScoreViewModel::class.java)
    }
}