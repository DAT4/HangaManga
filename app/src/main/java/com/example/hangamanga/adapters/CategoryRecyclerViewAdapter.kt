package com.example.hangamanga.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hangamanga.databinding.CardviewCategoryBinding
import com.example.hangamanga.models.Category
import com.example.hangamanga.models.Word
import com.example.hangamanga.ui.fragments.PickToEditCategoryFragmentDirections
import com.example.hangamanga.ui.fragments.PickToPlayCategoryFragmentDirections
import kotlinx.android.synthetic.main.cardview_category.view.*

class CategoryRecyclerViewAdapter(
    private var categories: List<Pair<String, List<Word>>>,
    val play: Boolean,
) : RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(itemView: CardviewCategoryBinding) :
        RecyclerView.ViewHolder(itemView.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CardviewCategoryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.itemView.apply {
            title.text = category.first
            this.setOnClickListener {
                val action = when (play) {
                    true -> PickToPlayCategoryFragmentDirections
                        .actionPickToPlayCategoryFragmentToPlayGameFragment(category.getOne())
                    false -> PickToEditCategoryFragmentDirections
                        .actionPickToEditCategoryFragment2ToEditCategoryFragment(category.serialize())
                }
                this.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    private fun Pair<String, List<Word>>.getOne(): Word {
        return this.second[this.second.indices.random()]
    }

    private fun Pair<String, List<Word>>.serialize(): Category {
        return Category(this)
    }

}