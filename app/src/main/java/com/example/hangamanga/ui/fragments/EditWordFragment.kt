package com.example.hangamanga.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hangamanga.R
import com.example.hangamanga.databinding.FragmentEditWordBinding
import com.example.hangamanga.databinding.FragmentLoginBinding

class EditWordFragment : Fragment() {
    private lateinit var _binding: FragmentEditWordBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("Hello")
    }
}