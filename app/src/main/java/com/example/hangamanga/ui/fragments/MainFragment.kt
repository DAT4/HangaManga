package com.example.hangamanga.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.hangamanga.R
import com.example.hangamanga.databinding.FragmentLoginBinding
import com.example.hangamanga.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var _binding: FragmentMainBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            play.setOnClickListener{
                root.findNavController().navigate(R.id.action_mainFragment_to_pickCategoryFragment)
            }
            highscores.setOnClickListener{
                root.findNavController().navigate(R.id.action_mainFragment_to_highScoreFragment)
            }
        }
    }
}