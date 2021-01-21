package com.example.firstkotlin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstkotlin.data.db.entity.Todo
import com.example.firstkotlin.data.repository.TodoRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoViewModel @Inject constructor(val todoRepo: TodoRepo) : ViewModel() {

    private val _todoMutableList = MutableLiveData<List<Todo>>()


    val todoList: LiveData<List<Todo>>
        get() = _todoMutableList


    fun getallDetail(): LiveData<List<Todo>> {
        return todoRepo.getallDetail()
    }

    fun addTodo(todo: Todo) = viewModelScope.launch {
        todoRepo.insert(todo)
    }


    fun deleteData(todo: Todo) = viewModelScope.launch {
        todoRepo.deleteData(todo)
    }


}
