package com.example.firstkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstkotlin.R
import com.example.firstkotlin.adapter.TodoRecycleAdapter
import com.example.firstkotlin.databinding.FragmentListBinding
import com.example.firstkotlin.viewModel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    lateinit var recycleAdap: TodoRecycleAdapter
    private val todoViewModel: TodoViewModel by viewModels()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentListBinding.bind(view)
        recycleAdap = TodoRecycleAdapter(todoViewModel,viewLifecycleOwner)
        context?.let {
            todoViewModel.todoEntityForCacheList.observe(viewLifecycleOwner, Observer {
                it?.let {
                    recycleAdap.initializeList(it)
                }
            })
        }
        binding.listRecycle.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recycleAdap
        }
    }
}
