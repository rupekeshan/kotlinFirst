package com.example.firstkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.firstkotlin.R
import com.example.firstkotlin.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val TAG = "MainFragment"
    private lateinit var navController: NavController
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        navController = Navigation.findNavController(view)
        binding.startButton.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_listFragment)
        }
        setHasOptionsMenu(true)

    }

}