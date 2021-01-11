package com.example.firstkotlin.viewModel

import android.content.Context
import androidx.lifecycle.*
import com.example.firstkotlin.data.entity.Todo
import com.example.firstkotlin.data.repository.TodoRepo
import kotlinx.coroutines.launch

class TodoViewModel() : ViewModel() {
    val todoList: LiveData<List<Todo>>? = null
    fun getallDetail(context: Context): LiveData<List<Todo>> {
        return TodoRepo.getallDetail(context)
    }

    fun addTodo(context: Context, todo: Todo) {
        TodoRepo.insert(context, todo)
    }


}
//
//class TodoVMFactory() : ViewModelProvider.Factory {
//    private val todoRepo: TodoRepo=TodoApplication().repository
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//
//        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return TodoViewModel(todoRepo) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}