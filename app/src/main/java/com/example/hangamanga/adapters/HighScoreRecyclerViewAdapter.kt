package com.example.hangamanga.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hangamanga.databinding.CardviewHighscoreBinding
import com.example.hangamanga.models.HighScore
import kotlinx.android.synthetic.main.cardview_highscore.view.*

class HighScoreRecyclerViewAdapter(
    private var highScores : List<HighScore>,
) : RecyclerView.Adapter<HighScoreRecyclerViewAdapter.HighScoreViewHolder>() {
    inner class HighScoreViewHolder(itemView: CardviewHighscoreBinding) : RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighScoreViewHolder {
        val binding = CardviewHighscoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HighScoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HighScoreViewHolder, position: Int) {
        val highScore = highScores[position]
        holder.itemView.apply {
            name.text = highScore.player
            word.text = highScore.word.word
            score.text = highScore.getScore().toString()
        }
    }

    override fun getItemCount(): Int {
        return highScores.size
    }
}