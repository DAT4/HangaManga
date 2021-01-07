package com.example.hangamanga.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hangamanga.R
import com.example.hangamanga.adapters.HighScoreRecyclerViewAdapter
import com.example.hangamanga.api.Resource
import com.example.hangamanga.databinding.FragmentEndGameBinding
import com.example.hangamanga.models.HighScore
import com.example.hangamanga.models.Word
import com.example.hangamanga.mvvm.score.ScoreViewModel
import com.example.hangamanga.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_end_game.*

class EndGameFragment : Fragment() {
    private lateinit var _binding: FragmentEndGameBinding
    private val binding get() = _binding

    private val args: EndGameFragmentArgs by navArgs()

    private lateinit var viewModel: ScoreViewModel

    private lateinit var score: HighScore
    private var winner: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        score = args.score
        winner = args.won

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEndGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).scoreViewModel

        viewModel.scores.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { scoresResponse ->
                        setupRecyclerView(scoresResponse)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e("PickCategoryFragment", "An error occured")
                    }
                }
                is Resource.Loading -> {
                    println("Loading")
                }
            }
        })

        binding.apply {
            playAgainBtn.setOnClickListener {
                val action =
                    EndGameFragmentDirections.actionEndGameFragmentToPickCategoryFragment()
                findNavController().navigate(action)
            }

            if (winner) {
                wordWord.setTextColor(Color.GREEN)
                stateImage.setImageResource(R.drawable.win)
                wordDescription.text = score.word.description
                //TODO Add the new score with retrofit here
            } else {
                wordWord.setTextColor(Color.RED)
                stateImage.setImageResource(R.drawable.loose)
                wordDescription.text = "You need to win do get the description."
                wordDescription.gravity = Gravity.CENTER_HORIZONTAL
            }
        }
    }

    private fun setupRecyclerView(scores: List<HighScore>) {
        val filteredScores = scores
            .sortedByDescending { it.getScore() }
            .filter { it.word.word == score.word.word }
        val adapter = HighScoreRecyclerViewAdapter(filteredScores)
        highscore_list.adapter = adapter
        highscore_list.layoutManager = LinearLayoutManager(activity)
    }
}