package com.example.firstkotlin.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.firstkotlin.data.repository.TodoRepo
import com.example.firstkotlin.model.Todo
import com.example.firstkotlin.util.DataState
import com.example.firstkotlin.util.Operations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepo: TodoRepo
) : ViewModel() {

//    init {
//        viewModelScope.launch {
//            todoRepo.syncWithCloud()
//        }
//    }

    var todoEntityForCacheList: LiveData<List<Todo>> = todoRepo.getallDetail().asLiveData()

    fun makeDataOperation(todo: Todo, operation: Operations) = liveData {
        emit(DataState.Loading)
        delay(2000L)
        when (operation) {
            Operations.ADD -> {
                val id = addTodo(todo)
                Log.e("TAG", "makeDataOperation: $id")
                emit(DataState.Success("close"))
            }
            Operations.DELETE -> {
                val id = deleteData(todo)
                Log.e("TAG", "makeDataOperation: $id")
                emit(DataState.Success("close"))
            }
        }

    }

    private suspend fun addTodo(todo: Todo): Long {
        return todoRepo.insert(todo)
    }

    private suspend fun deleteData(todo: Todo): Int {
        return todoRepo.deleteData(todo)
    }

}
