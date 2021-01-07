package com.example.hangamanga.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hangamanga.adapters.CategoryRecyclerViewAdapter
import com.example.hangamanga.api.Resource
import com.example.hangamanga.databinding.FragmentPickToEditCategoryBinding
import com.example.hangamanga.models.Word
import com.example.hangamanga.mvvm.word.WordViewModel
import com.example.hangamanga.ui.MainActivity

class PickToEditCategoryFragment : Fragment() {
    private lateinit var _binding: FragmentPickToEditCategoryBinding
    private val binding get() = _binding

    private lateinit var viewModel: WordViewModel
    private lateinit var categoryAdapter: CategoryRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPickToEditCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getCategories(words: List<Word>): List<Pair<String,List<Word>>>{
        return words.groupBy {
            it.category
        }.toList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).wordViewModel

        viewModel.words.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { wordsResponse ->
                        setupRecyclerView(wordsResponse)
                    }
                }
                is Resource.Error -> {
                    response.message?.let {  message ->
                        Log.e("PickCategoryFragment", "An error occured")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun setupRecyclerView(words: List<Word>) {
        categoryAdapter = CategoryRecyclerViewAdapter(getCategories(words), false)
        binding.list.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun hideProgressBar() {
        binding.loader.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.loader.visibility = View.VISIBLE
    }
}