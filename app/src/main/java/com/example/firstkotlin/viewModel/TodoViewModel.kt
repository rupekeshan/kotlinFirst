package com.example.firstkotlin.viewModel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.firstkotlin.data.repository.TodoRepo
import com.example.firstkotlin.model.Todo
import kotlinx.coroutines.launch

class TodoViewModel @ViewModelInject constructor(
    val todoRepo: TodoRepo,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _todoMutableList = MutableLiveData<List<Todo>>()


    val todoEntityForCacheList: LiveData<List<Todo>>
        get() = _todoMutableList


    fun getallDetail(): List<Todo> {
        return todoRepo.getallDetail()
    }

    fun addTodo(todo: Todo) = viewModelScope.launch {
        todoRepo.insert(todo)
    }


    fun deleteData(todo: Todo) = viewModelScope.launch {
        todoRepo.deleteData(todo)
    }


}
