package com.example.hangamanga.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hangamanga.databinding.CardviewHategoryBinding
import com.example.hangamanga.models.Hategory

class HategoryViewPagerAdapter(
    val hategories : List<Hategory>
) : RecyclerView.Adapter<HategoryViewPagerAdapter.HategoryViewHolder>() {
    inner class HategoryViewHolder(binding: CardviewHategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HategoryViewHolder {
        val binding = CardviewHategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HategoryViewHolder, position: Int) {
        val hategory = hategories[position]
        holder.itemView.apply {

        }
    }

    override fun getItemCount(): Int {
        return hategories.size
    }
}
