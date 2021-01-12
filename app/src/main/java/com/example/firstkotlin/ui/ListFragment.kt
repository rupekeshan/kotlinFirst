package com.example.firstkotlin.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstkotlin.R
import com.example.firstkotlin.adapter.TodoRecycleAdapter
import com.example.firstkotlin.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
    private  val TAG = "ListFragment"

    private lateinit var recycleAdap: TodoRecycleAdapter
    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recycleAdap = TodoRecycleAdapter()
        context?.let {
            todoViewModel.getallDetail(it).observe(viewLifecycleOwner, Observer {
                it?.let {
                    recycleAdap.initializeList(it)
                }
            })
        }
        listRecycle.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recycleAdap
        }

    }
}