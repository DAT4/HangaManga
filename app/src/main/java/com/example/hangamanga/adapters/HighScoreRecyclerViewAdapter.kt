package sh.mama.hangman.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sh.mama.hangman.models.HighScore
import java.util.*

class HighScoreRecyclerViewAdapter(
        private var highScores : List<HighScore>,
) : RecyclerView.Adapter<HighScoreRecyclerViewAdapter.HighScoreViewHolder>() {
    inner class HighScoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighScoreViewHolder{
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.event_card_fragment, parent, false)
        return HighScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: HighScoreViewHolder, position: Int) {
        val highScore = highScores[position]
        holder.itemView.apply {
        }
    }

    override fun getItemCount(): Int {
        return highScores.size
    }
}