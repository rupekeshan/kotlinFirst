package com.example.firstkotlin.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.firstkotlin.data.db.entity.Todo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo_first")
    fun getAllDataWithLive(): LiveData<List<Todo>>

    @Query("SELECT * FROM todo_first")
    fun getAllData(): List<Todo>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addData(todo:Todo)

    @Delete
    suspend fun deleteData(todo:Todo)

}