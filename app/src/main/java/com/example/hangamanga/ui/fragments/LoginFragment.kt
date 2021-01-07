package com.example.hangamanga.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hangamanga.api.RetrofitInstance
import com.example.hangamanga.api.TokenHolder
import com.example.hangamanga.databinding.FragmentLoginBinding
import com.example.hangamanga.models.User
import com.example.hangamanga.ui.MainActivity
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private lateinit var _binding: FragmentLoginBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            login.setOnClickListener {
                lifecycleScope.launch {
                    val response = RetrofitInstance
                        .api
                        .login(
                            User(
                                username = username.text.toString(),
                                password = password.text.toString()
                            )
                        )

                    when (response.isSuccessful) {
                        true -> {
                            TokenHolder.token = response.body()!!.token
                            Toast.makeText(requireContext(), "Logged in", Toast.LENGTH_SHORT)
                            (activity as MainActivity).bottomMenu.menu[2].isEnabled = false
                            findNavController().popBackStack()
                        }
                        false -> Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT)
                            .show()
                    }


                }
            }
        }
    }
}