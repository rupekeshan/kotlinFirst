package com.example.firstkotlin.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.firstkotlin.R
import com.example.firstkotlin.data.db.RoomDB
import com.example.firstkotlin.data.db.entity.Todo
import com.example.firstkotlin.data.repository.TodoRepo
import com.example.firstkotlin.viewModel.TodoViewModel
import com.example.firstkotlin.viewModel.vmfactory.TodoVMFactory
import kotlinx.android.synthetic.main.fragment_detailed_todo.*


class Detailed_todo : Fragment() {

    private lateinit var todoViewModel: TodoViewModel
    private var sampleData: List<Todo>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_todo, container, false)
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
        val id = arguments?.getInt("id")
        context?.let { context ->
            todoViewModel.getallDetail().observe(viewLifecycleOwner, Observer {
                sampleData = it
                if (id != null) {
                    updateData(id)
                }
                else{
                    Toast.makeText(context,"Something Wrong",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun updateData(id:Int) {
        val getClass = id.let {
            sampleData?.find {
                it.id == id
            }
        }
        header_detail.text = getClass?.header
        desc_detail.text = getClass?.body
    }
}