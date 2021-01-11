package com.example.firstkotlin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.firstkotlin.R
import com.example.firstkotlin.data.entity.Todo
import com.example.firstkotlin.viewModel.TodoViewModel
import kotlinx.android.synthetic.main.fragment_detailed_todo.*


class Detailed_todo : Fragment() {

    private val todoViewModel: TodoViewModel by viewModels()
    private var sampleData: List<Todo>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments?.getInt("id")
        context?.let {
            todoViewModel.getallDetail(it).observe(viewLifecycleOwner, Observer {
                sampleData = it
            })
        }

        val getClass = id?.let {
            sampleData?.find {
                it.id == id
            }
        }
        header_detail.text = getClass?.header
        desc_detail.text = getClass?.body
    }

}