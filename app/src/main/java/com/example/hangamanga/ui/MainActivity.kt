package com.example.hangamanga.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hangamanga.databinding.ActivityMainBinding
import com.example.hangamanga.observer.ConcreteScores
import com.example.hangamanga.observer.ConcreteWords

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ConcreteScores.cache()
        ConcreteWords.cache()
    }
}