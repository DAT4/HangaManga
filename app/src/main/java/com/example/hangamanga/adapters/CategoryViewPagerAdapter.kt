package com.example.hangamanga.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sh.mama.hangman.models.Hategory

class HategoryViewPagerAdapter(
    val hategories : List<Hategory>
) : RecyclerView.Adapter<HategoryViewPagerAdapter.HategoryViewHolder>() {
    inner class HategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HategoryViewHolder {
        val binding =
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item_view_pager, parent, false)
        return HategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HategoryViewHolder, position: Int) {
        val hategory = hategories[position]
        holder.itemView.apply {

        }

        override fun getItemCount(): Int {
            return hategories.size
        }
    }
}
