package com.example.hangamanga.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hangamanga.databinding.CardviewHighscoreCategoryBinding
import com.example.hangamanga.models.HighScore
import kotlinx.android.synthetic.main.cardview_highscore_category.view.*
import java.lang.Exception

class CategoryHighScoreViewpagerAdapter(
    val categories : List<Pair<String,List<HighScore>>>
) : RecyclerView.Adapter<CategoryHighScoreViewpagerAdapter.CategoryHighScoreViewHolder>() {
    inner class CategoryHighScoreViewHolder(binding: CardviewHighscoreCategoryBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHighScoreViewHolder {
        val binding = CardviewHighscoreCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHighScoreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryHighScoreViewHolder, position: Int) {
        val category = categories[position]
        holder.itemView.apply {
            title.text = category.first
            category.second.forEach{
                println(it.getScore())
            }
            try {
                list.adapter = HighScoreRecyclerViewAdapter(category.second)
                list.layoutManager = LinearLayoutManager(this.context)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}
