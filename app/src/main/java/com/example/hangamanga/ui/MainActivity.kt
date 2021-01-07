package com.example.hangamanga.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.hangamanga.databinding.ActivityMainBinding
import com.example.hangamanga.mvvm.score.ScoreDatabase
import com.example.hangamanga.mvvm.score.ScoreRepository
import com.example.hangamanga.mvvm.score.ScoreViewModel
import com.example.hangamanga.mvvm.score.ScoreViewModelProviderFactory
import com.example.hangamanga.mvvm.user.UserDatabase
import com.example.hangamanga.mvvm.user.UserRepository
import com.example.hangamanga.mvvm.user.UserViewModel
import com.example.hangamanga.mvvm.user.UserViewModelProviderFactory
import com.example.hangamanga.mvvm.word.WordDatabase
import com.example.hangamanga.mvvm.word.WordRepository
import com.example.hangamanga.mvvm.word.WordViewModel
import com.example.hangamanga.mvvm.word.WordViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    lateinit var wordViewModel: WordViewModel
    lateinit var scoreViewModel: ScoreViewModel
    lateinit var userViewModel: UserViewModel

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

        val userRepository = UserRepository(UserDatabase(this))
        val userViewModelProviderFactory = UserViewModelProviderFactory(userRepository)
        userViewModel = ViewModelProvider(this, userViewModelProviderFactory).get(UserViewModel::class.java)
    }
}
