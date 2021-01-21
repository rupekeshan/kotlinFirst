package com.example.firstkotlin.viewModel.vmfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firstkotlin.data.repository.TodoRepo
import com.example.firstkotlin.viewModel.TodoViewModel

class TodoVMFactory(val todoRepo: TodoRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(todoRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
