package com.example.hangamanga.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.hangamanga.adapters.CategoryHighScoreViewpagerAdapter
import com.example.hangamanga.api.Resource
import com.example.hangamanga.databinding.FragmentHighscoreBinding
import com.example.hangamanga.models.HighScore
import com.example.hangamanga.mvvm.Score.ScoreViewModel
import com.example.hangamanga.observer.ConcreteScores
import com.example.hangamanga.ui.MainActivity

class HighScoreFragment : Fragment() {
    private lateinit var _binding: FragmentHighscoreBinding
    private val binding get() = _binding

    private lateinit var viewModel: ScoreViewModel
    private lateinit var categoryAdapter: CategoryHighScoreViewpagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHighscoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).scoreViewModel

        viewModel.scores.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { scoresResponse ->
                        setupViewPager(scoresResponse)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e("PickCategoryFragment", "An error occured")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })

    }

    private fun setupViewPager(scores: ArrayList<HighScore>) {
        val adapter = CategoryHighScoreViewpagerAdapter(categories)
        try {
            binding.viewpager.adapter = adapter
        } catch (e: java.lang.Exception) {
            println(e)
        }
    }

    private fun getCategories(scores: ArrayList<HighScore>): List<Pair<String, List<HighScore>>> {
        return scores.groupBy {
            it.word.category
        }.toList()
    }

    private fun hideProgressBar() {
        binding.loader.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.loader.visibility = View.VISIBLE
    }
}