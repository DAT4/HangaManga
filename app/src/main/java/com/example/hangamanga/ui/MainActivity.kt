package com.example.hangamanga.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.hangamanga.R
import com.example.hangamanga.databinding.ActivityMainBinding
import com.example.hangamanga.mvvm.score.ScoreDatabase
import com.example.hangamanga.mvvm.score.ScoreRepository
import com.example.hangamanga.mvvm.score.ScoreViewModel
import com.example.hangamanga.mvvm.score.ScoreViewModelProviderFactory
import com.example.hangamanga.mvvm.word.WordDatabase
import com.example.hangamanga.mvvm.word.WordRepository
import com.example.hangamanga.mvvm.word.WordViewModel
import com.example.hangamanga.mvvm.word.WordViewModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var wordViewModel: WordViewModel
    lateinit var scoreViewModel: ScoreViewModel
    lateinit var bottomMenu: BottomNavigationView

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

        bottomMenu = binding.bottomNavigationView
        val navController = findNavController(R.id.fragment)
        bottomMenu.setupWithNavController(navController)
    }
}

