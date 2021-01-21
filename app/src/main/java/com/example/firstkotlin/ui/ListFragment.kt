package com.example.firstkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstkotlin.R
import com.example.firstkotlin.adapter.TodoRecycleAdapter
import com.example.firstkotlin.data.db.RoomDB
import com.example.firstkotlin.data.repository.TodoRepo
import com.example.firstkotlin.viewModel.TodoViewModel
import com.example.firstkotlin.viewModel.vmfactory.TodoVMFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : Fragment() {

    @Inject lateinit var recycleAdap: TodoRecycleAdapter
    @Inject lateinit var todoViewModel: TodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }
    
    

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        todoViewModel = ViewModelProvider(
            this,
            TodoVMFactory(TodoRepo(
                RoomDB(
                    requireContext()
                ),requireContext()
            ))
        ).get(TodoViewModel::class.java)
        recycleAdap = TodoRecycleAdapter(todoViewModel)
        context?.let {
            todoViewModel.getallDetail().observe(viewLifecycleOwner, Observer {
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
