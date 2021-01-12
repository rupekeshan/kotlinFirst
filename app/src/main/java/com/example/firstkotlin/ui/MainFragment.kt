package com.example.firstkotlin.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.firstkotlin.R
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private val TAG = "MainFragment"
    private lateinit var button: Button
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        button = startButton
        button.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_listFragment)
        }
        setHasOptionsMenu(true)

    }

}