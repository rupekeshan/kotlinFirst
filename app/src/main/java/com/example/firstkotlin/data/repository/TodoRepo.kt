package com.example.firstkotlin.data.repository

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.firstkotlin.data.RoomDB
import com.example.firstkotlin.data.dao.TodoDao
import com.example.firstkotlin.data.entity.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TodoRepo(private val todoDao: TodoDao) {
    companion object{
        var roomDB:RoomDB?=null

        fun initDb(context: Context):RoomDB{
            return RoomDB.getDb(context)

        }

        fun insert(context:Context,todo: Todo){
            roomDB= initDb(context)
            CoroutineScope(Dispatchers.IO).launch {
                roomDB!!.todoDao().addData(todo)
            }

        }

        fun getallDetail(context: Context): LiveData<List<Todo>> {
            roomDB= initDb(context)
            return roomDB!!.todoDao().getAllData().asLiveData()
        }

        fun deleteData(todo:Todo,context:Context){
            roomDB = initDb(context = context)
            CoroutineScope(Dispatchers.IO).launch {
                roomDB!!.todoDao().deleteData(todo)
            }
        }
    }

}