package com.example.fifa.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.fifa.R
import com.example.fifa.databinding.FragmentFailSearchBinding

class FailSearch : Fragment() {
    private lateinit var binding: FragmentFailSearchBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFailSearchBinding.inflate(layoutInflater)
        val view = binding.root

        backHome()

        return view
    }

    private fun backHome() {
        binding.returnBack.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.back_action)
        }
    }
}