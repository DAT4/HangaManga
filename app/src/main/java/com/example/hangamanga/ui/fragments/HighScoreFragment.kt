package com.example.hangamanga.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hangamanga.adapters.CategoryHighScoreViewpagerAdapter
import com.example.hangamanga.databinding.FragmentHighscoreBinding
import com.example.hangamanga.observer.ConcreteScores
import com.example.hangamanga.observer.IObserver

class HighScoreFragment : Fragment(), IObserver {
    private lateinit var _binding: FragmentHighscoreBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConcreteScores.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ConcreteScores.remove(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHighscoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        printScores()
    }

    private fun printScores(){
        val categories = ConcreteScores.getCategories()
        val adapter = CategoryHighScoreViewpagerAdapter(categories)
        try {
            binding.viewpager.adapter = adapter
        } catch (e: java.lang.Exception) {
            println(e)
        }
    }

    override fun update() {
        printScores()
    }
}